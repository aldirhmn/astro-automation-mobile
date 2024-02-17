package login

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as m
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidElement
import io.appium.java_client.android.nativekey.AndroidKey
import io.appium.java_client.android.nativekey.KeyEvent

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When

import helpers.*

class LoginCase {

	static ObjectLocator mobile = new ObjectLocator();
	static Support sup = new Support();

	static AndroidKey getDigitKey(int number) {
		switch (number) {
			case 0: return AndroidKey.DIGIT_0;
			case 1: return AndroidKey.DIGIT_1;
			case 2: return AndroidKey.DIGIT_2;
			case 3: return AndroidKey.DIGIT_3;
			case 4: return AndroidKey.DIGIT_4;
			case 5: return AndroidKey.DIGIT_5;
			case 6: return AndroidKey.DIGIT_6;
			case 7: return AndroidKey.DIGIT_7;
			case 8: return AndroidKey.DIGIT_8;
			case 9: return AndroidKey.DIGIT_9;
			default: return null;
		}
	}
	
	@Given("User open the Astro Application")
	def openApplication() {

		'Get Project Directory'
		String projectDir = RunConfiguration.getProjectDir()

		'Open The Application'
		m.startApplication(projectDir+"/APK/$GlobalVariable.FileNameAPK", true)

		'Conditional'
		if (m.waitForElementPresent(mobile.object("xpath", '//android.widget.TextView[@text="Mulai pakai Astro" and @class="android.widget.TextView"]'), 10)) {

			'Take Screenshot'
			sup.takeScreenshot()

			'Mobile Tap Skips Ads'
			m.tap(mobile.object("xpath", '//android.widget.TextView[@text="Mulai pakai Astro" and @class="android.widget.TextView"]'), 5)
		}

		'Wait Element Agreement'
		m.waitForElementPresent(mobile.object("xpath", '//android.widget.TextView[@text="Agree" and @class="android.widget.TextView"]'), 10)

		'Take Screenshot'
		sup.takeScreenshot()

		'Tap Agree'
		m.tap(mobile.object("xpath", '//android.widget.TextView[@text="Agree" and @class="android.widget.TextView"]'), 5)
	}

	@When("User input their valid phone number into Login Page with data {string}")
	def inputPhoneNumber(String phoneNumber) {
		
		'Conditional'
		if(m.waitForElementPresent(mobile.object("resource-id", "com.google.android.gms:id/title"), 5)) {
			
			'Take Screenshot'
			sup.takeScreenshot()
			
			'Tap CLose Suggestion'
			m.tap(mobile.object("resource-id", "com.google.android.gms:id/cancel"), 5)
		}

		'Verify Element'
		m.verifyElementExist(mobile.object("xpath", '//android.widget.TextView[@text="Nomor Handphone" and @class="android.widget.TextView"]'), 10)

		'Take Screenshot'
		sup.takeScreenshot()

		'Input Phone Number'
		m.tap(mobile.object("xpath", '//android.widget.TextView[@text="Nomor Handphone" and @class="android.widget.TextView"]'), 5)

		'Mobile Input'
		m.sendKeys(mobile.object("xpath", '//android.widget.EditText[@class="android.widget.EditText"]'), phoneNumber)

		'Take Screenshot'
		sup.takeScreenshot()
		
		'Open Notification'
		m.openNotifications()
		
		'Conditional'
		if(m.waitForElementPresent(mobile.object("xpath", "//android.widget.FrameLayout/android.widget.TextView"), 5)) {
			
			'Clear Notification'
			m.tap(mobile.object('resource-id', 'com.android.systemui:id/clear_all'), 5)
		} else {
			
			'Close Notification'
			m.closeNotifications()
		}

		'Mobile Tap Lanjutkan'
		m.tap(mobile.object("xpath", '//android.widget.Button[@class="android.widget.Button"]'), 5)
	}

	@And("User input OTP that sended")
	def inputOTP() {

		'Verify Element'
		m.verifyElementExist(mobile.object("xpath", '//android.widget.TextView[@text="Kode OTP" and @class="android.widget.TextView"]'), 10)

		'Take Screenshot'
		sup.takeScreenshot()

		'Open Notification'
		m.openNotifications()
	
		'Getting Raw Message From OTP'
		String textOtp = m.getText(mobile.object("xpath", "//android.widget.FrameLayout/android.widget.TextView"), 10)
		
		'Filtering Only The Number Only'
		String clearText = textOtp.replaceAll("[^0-9]", "")
		
		'Close Notification'
		m.closeNotifications()
		
		m.sendKeys(mobile.object("xpath", '//android.widget.HorizontalScrollView[@class="android.widget.HorizontalScrollView"]'), clearText)
	}

	@Then("User successfully Login into Astro Application and Direct to HomePage")
	def stepVerification() {
		
		'Take Screenshot'
		sup.takeScreenshot()
		
		'Conditional Permission'
		if(m.waitForElementPresent(mobile.object("resource-id", "com.android.permissioncontroller:id/permission_icon"), 5)) {
			
			'Tap Allow'
			m.tap(mobile.object("resource-id", "com.android.permissioncontroller:id/permission_allow_foreground_only_button"), 5)
			
			'Tap Allow'
			m.tap(mobile.object("resource-id", "com.android.permissioncontroller:id/permission_allow_button"), 5)
		}
		
		'Conditional'
		if(m.waitForElementPresent(mobile.object("resource-id", "com.astro.shop:id/interstitial_image"), 5)) {
			
			'Take Screenshot'
			sup.takeScreenshot()
			
			'Tap Button Close'
			m.tap(mobile.object("xpath", '(//android.widget.ImageView[@class="android.widget.ImageView"])[1]'), 5)
			
			'Take Screenshot'
			sup.takeScreenshot()
		}
		
		'Verify Element Exist'
		m.verifyElementExist(mobile.object("resource-id", "homeSearchEntryPoint"), 10)
	}
}