# automated-ui-testing
This repository contains Java code for automated testing using Selenium WebDriver. The script interacts with a sample web page to demonstrate various functionalities.

---

#### Functions:

1. **initializeDriver():**
   - Initializes the ChromeDriver for Selenium WebDriver.
   - Navigates to the specified URL.

2. **fillnames(WebDriver driver):**
   - Enters values into the "First Name" and "Last Name" fields on the web page.
   - Checks if the fields are enabled and prints status.

3. **singleselect(WebDriver driver):**
   - Demonstrates single-select dropdown functionality.
   - Selects the "Audi" option from the dropdown and prints the selected option.

4. **multiselect(WebDriver driver):**
   - Demonstrates multi-select dropdown functionality.
   - Selects multiple options and prints all options and selected options.

5. **alertbox(WebDriver driver):**
   - Interacts with alert boxes on the web page.
   - Handles simple alerts, confirmation alerts, and prompts.

6. **alert(WebDriver driver):**
   - Waits for an alert to be present and prints the alert message.
   - Accepts the alert.

7. **brokenLinks(WebDriver driver):**
   - Identifies and checks the status of all hyperlinks on the web page.
   - Prints whether each link is valid or broken.

#### Libraries and Classes:

- **WebDriver:** Selenium's WebDriver for browser automation.
- **Alert:** Selenium's Alert class for handling alerts.
- **Select:** Selenium's Select class for interacting with dropdowns.
- **WebDriverWait:** Selenium's WebDriverWait for waiting for alerts.
- **HttpURLConnection:** Java's HttpURLConnection for checking link status.

#### Other Libraries:

- **guava-jre.jar**
- **failsafe.jar**
- **selenium-java**
---
