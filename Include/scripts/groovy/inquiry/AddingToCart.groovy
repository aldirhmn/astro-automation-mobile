package inquiry
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
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver

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

class AddingToCart {

	static ObjectLocator mobile = new ObjectLocator();
	static Support sup = new Support();

	@Given("User already have List Product to Add")
	def listProduct() {

		'Verify Element'
		m.verifyElementExist(mobile.object("resource-id", "productCard"), 5)

		'Take Screenshot'
		sup.takeScreenshot()
	}

	@When("User tap plus button to Add to Cart")
	def addProduct() {

		'Calling Android Driver'
		AndroidDriver<?> driver = MobileDriverFactory.getDriver();

		'List All Element'
		List<MobileElement> listProduct = driver.findElements(By.xpath('(//android.widget.Button[@class="android.widget.Button"])'))

		'Calilng Random Library'
		Random rand = new Random()

		'Variable Random Index'
		int randomIndex = 1 + rand.nextInt(listProduct.size())

		'Tap Plus Button From Random Index'
		m.tap(mobile.object("xpath", "(//android.widget.Button[@class='android.widget.Button'])[$randomIndex]"), 5)
	}

	@Then("User successfully adding Product to Cart")
	def productCart() {

		'Variable Product Contains'
		String getAmount = m.getText(mobile.object("xpath", '(//android.widget.TextView[@text="Subtotal"]/..//android.widget.TextView)[3]'), 5)

		'Object Cart'
		TestObject objectCart = new TestObject().addProperty("xpath", ConditionType.EQUALS, "(//android.widget.TextView[@text='$getAmount' and @class='android.widget.TextView'])[2]")

		'Verify Element Present'
		m.verifyElementExist(objectCart, 0)

		'Take Screenshot'
		sup.takeScreenshot()

		'Tap Cart'
		m.tap(objectCart, 5)

		'Wait For Element Present'
		m.waitForElementPresent(mobile.object("xpath", '//android.widget.TextView[@text="Keranjang" and @class="android.widget.TextView"]'), 5)

		'Take Screenshot'
		sup.takeScreenshot()
		
		'Close Application'
		m.closeApplication()
	}

	//======== Another Step in Feature
	@And("User tap the Product to Detail Product")
	def detailProduct() {

		'Calling Android Driver'
		AndroidDriver<?> driver = MobileDriverFactory.getDriver();

		'List All Element'
		List<MobileElement> listProduct = driver.findElements(By.xpath('(//android.view.View[@resource-id="productCard"])'))

		'Calilng Random Library'
		Random rand = new Random()

		'Variable Random Index'
		int randomIndex = 1 + rand.nextInt(listProduct.size())

		'Tap Plus Button From Random Index'
		m.tap(mobile.object("xpath", "(//android.view.View[@resource-id='productCard'])[$randomIndex]"), 5)

	}

	@When("User tap button Keranjang in Detail Product")
	def btnKeranjang() {

		'Object Cart'
		TestObject btn_keranjang = new TestObject().addProperty("xpath", ConditionType.EQUALS, '//android.widget.TextView[@text="Keranjang" and @class="android.widget.TextView"]')

		'Verify Element Exist'
		m.verifyElementExist(btn_keranjang, 5)

		'Tap Keranjang'
		m.tap(btn_keranjang, 5)
	}

	@And("User tap some Kategori in Home Page")
	def tapKategoriesMenu() {

		'Object Cart'
		TestObject btn_menu = new TestObject().addProperty("xpath", ConditionType.EQUALS, '//android.widget.TextView[@text=" Spesial Pengguna Baru" and @class="android.widget.TextView"]')

		'Verify Element Exist'
		m.verifyElementExist(btn_menu, 5)

		'Tap Keranjang'
		m.tap(btn_menu, 5)
	}
}