:: To ensure that the "run-allure-report.bat" file runs the report seamlessly,
:: it's essential to have the "Batch Scripts Support" or "CMD Support" plugin installed.
:: This plugin facilitates the execution of batch scripts directly from the IntelliJ IDEA interface, enhancing the overall user experience.

:: Once the plugins are installed you can run the report by
    :: * Right click on "run-allure-report.bat"
    :: * click on "Run 'run-allure-report.bat' "


:: Generate the Allure report and open in web browser
:: Please replace "C:\Users\Zoki\IdeaProjects\DemoQA_Automated_UI_Testing\allure-results" with the actual path to your project.
allure serve C:\Users\Zoki\IdeaProjects\DemoQA_Automated_UI_Testing\allure-results