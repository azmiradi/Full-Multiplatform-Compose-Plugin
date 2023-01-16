# Full-Multiplatform-Compose-Plugin

An intellij plugin to create multiplatform compose projects, covering everything! JS, Android, IOS, and desktop!

To use, just go to File -> New Project -> Multiplatform Compose; then select what platforms you want to support!
Next, change some options like package name and app names! Then! Create! And the entire project will be
generated for you!

No more needing to spend hours trying to figure out how to get a full multiplatform project together!
No need to copy files from one new project to another to get all the platforms together in one project!

<p align="center">
  <img src="/screenshots/plugin_start.png" width="32%"/>
  <img src="/screenshots/plugin_options.png" width="32%"/>
</p>

Note: IOS will ONLY work on macOS computers. I am sorry.

# Running

I tried to make this plugin as plug-n-play as possible!
Android's run configuration should be in place already.

Desktop, web, and iOS are a bit special.

### For Desktop:

Either run `MainKt` or find the `main.kt` file and press run on the gutter!

### For Web:

Either run `Run Web` or run `./gradlew :jsApp:jsBrowserDevelopmentRun`

### For iOS:

Open the `iosApp` in XCode via `iosApp.xcworkspace` and run as normal from there.

# Installing

Either download and manually install
from [here](https://github.com/jakepurple13/Full-Multiplatform-Compose-Plugin/releases/latest)
OR find it on the [JetBrains Marketplace](https://plugins.jetbrains.com/plugin/20800-full-multiplatform-compose/versions/stable/276699)
