Mobile Automation Framework

A robust Mobile Test Automation Framework in Java designed for Android/iOS testing using industry-standard tools and patterns.

This repository provides a structure to write, organize, and execute automated mobile tests (e.g., Appium with Java) — following best practices for maintainability and scalability, including Page Object Model, configuration management, and test execution guidelines.

🔧 Features

📌 Structured project layout for scalable test suites

📱 Supports mobile automation (Android / iOS)

🧪 Easy configuration and test execution

📊 Logging and reporting support

🚀 Ready to integrate with CI/CD

🔄 Expandable with new test modules

🧱 Tech Stack
Component	Technology
Language	Java
Build Tool	Maven
Automation	Appium, Selenium
Testing	JUnit / TestNG (if included)
Reporting	(Add your chosen tool/report type here)
🚀 Getting Started
🛠 Prerequisites

Make sure the following are installed:

Java JDK 11+

Maven 3.x

Appium Server (2.x)

Node.js & npm

Android SDK / iOS tools

Optional: IntelliJ IDEA or Eclipse

⬇️ Installation

Clone this repo:

git clone https://github.com/michaelFisher-qaEngineer/MobileAutomationFramework.git
cd MobileAutomationFramework


Install dependencies:

mvn clean install

⚙️ Configuration

Modify configuration files located under src/test/resources:

config.properties — global settings

android-config.properties — Android device/app config

ios-config.properties — iOS device/app config

logging.properties — logging preferences

▶️ Running Tests

Run all tests with:

mvn test


Or run individual test suites:

mvn test -Dgroups=Smoke


Adjust flags (-Dtags, -Denv) based on your configuration.

📈 Reporting

After execution, test reports can be found in:

target/surefire-reports — JUnit test results

(Add custom location if using other reporting tools)

Screenshots and logs are stored under configured directories for easier debugging.

🤝 Contributing

Contributions are welcome!

Fork this repository

Create a feature branch

git checkout -b feature/my-new-test


Commit your changes

git commit -m "Add awesome new test"


Push to your branch

git push origin feature/my-new-test


Maintained by Michael Fisher — QA Engineer