package com.programmersbox.fullmultiplatformcompose

import com.intellij.ide.util.projectWizard.ModuleBuilder
import com.intellij.ide.util.projectWizard.ModuleWizardStep
import com.intellij.ide.util.projectWizard.WizardContext
import com.intellij.openapi.Disposable
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.module.ModuleType
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ModifiableRootModel
import com.intellij.openapi.roots.ui.configuration.ModulesProvider
import com.intellij.openapi.util.io.FileUtil
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiManager
import com.intellij.psi.impl.file.PsiDirectoryFactory
import com.programmersbox.fullmultiplatformcompose.generators.CommonGenerator
import com.programmersbox.fullmultiplatformcompose.utils.backgroundTask
import com.programmersbox.fullmultiplatformcompose.utils.runGradle
import java.io.File

class BuilderWizardBuilder : ModuleBuilder() {
    override fun getModuleType(): ModuleType<*> = BuilderModuleType()

    var hasAndroid: Boolean = false
    var hasWeb: Boolean = false
    var hasiOS: Boolean = false
    var hasDesktop: Boolean = false

    override fun setupRootModel(modifiableRootModel: ModifiableRootModel) {
        val root = createAndGetRoot() ?: return
        modifiableRootModel.addContentEntry(root)
        try {
            ApplicationManager.getApplication().runWriteAction {
                val manager = PsiManager.getInstance(modifiableRootModel.project)
                manager.findFile(root)?.add(
                    PsiDirectoryFactory.getInstance(manager.project)
                        .createDirectory(root.createChildDirectory(null, "webpack"))
                )
            }
        } catch (ex: java.lang.Exception) {
            ex.printStackTrace()
        }

        modifiableRootModel.project.backgroundTask("Setting up project") {
            try {
                val generator = CommonGenerator(
                    hasAndroid = hasAndroid,
                    hasDesktop = hasDesktop,
                    hasiOS = hasiOS,
                    hasWeb = hasWeb
                )
            } catch (ex: Exception) {
            }
            installGradleWrapper(modifiableRootModel.project)
        }
    }

    private fun createAndGetRoot(): VirtualFile? {
        val path = contentEntryPath?.let { FileUtil.toSystemIndependentName(it) } ?: return null
        return LocalFileSystem.getInstance().refreshAndFindFileByPath(File(path).apply { mkdirs() }.absolutePath)
    }

    private fun installGradleWrapper(project: Project) {
        project.runGradle("wrapper --gradle-version 7.6 --distribution-type all")
    }

    override fun getCustomOptionsStep(context: WizardContext?, parentDisposable: Disposable?): ModuleWizardStep {
        return FirstStep(this)
    }

    override fun createWizardSteps(
        wizardContext: WizardContext,
        modulesProvider: ModulesProvider
    ): Array<ModuleWizardStep> {
        //TODO: Second step will be actual text info like package names
        return arrayOf(

        )
    }

}