package homepage

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
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
import login.LoginCase

class SearchProduct {

	static ObjectLocator mobile = new ObjectLocator();
	static Support sup = new Support();
	static LoginCase loginPage = new LoginCase()

	@Given("User already in Home Page")
	def inHomePage() {
		
		'Conditional Loop'
		while(m.waitForElementNotPresent(mobile.object("resource-id", "homeSearchEntryPoint"), 3)) {
			
			'Press Back'
			m.pressBack()
		}

		'Calling Method in Login Page'
		loginPage.stepVerification()
	}

	@And("User tap Search Product")
	def tapSearch() {

		'Tap Button Search'
		m.tap(mobile.object("resource-id", "homeSearchEntryPoint"), 5)
	}

	@When("User search some Product in Field Search Product with {string}")
	def searchProduct(String product) {

		'Verify Element Present'
		m.verifyElementExist(mobile.object("xpath", '//android.widget.EditText[@class="android.widget.EditText"]'), 5)
		
		'Take Screenshot'
		sup.takeScreenshot()

		'Input Product'
		m.sendKeys(mobile.object("xpath", '//android.widget.EditText[@class="android.widget.EditText"]'), product)
	}
	
	@When("User tap Recommandation Populer Product")
	def tapRecommendProduct() {
		
		'Tap Recommendation Product'
		m.tap(mobile.object("xpath", '(//android.view.View[@resource-id="searchKeywordChip"])[1]'), 3)
	}

	@Then("User successfully show Product")
	def showProduct() {
		
		'Take Screenshot'
		sup.takeScreenshot()
		
		'Verify Element'
		m.verifyElementExist(mobile.object("xpath", '(//android.view.View[@resource-id="productCard"])[1]'), 5)
	}
}