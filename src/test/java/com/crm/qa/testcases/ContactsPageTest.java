/*author: PARIMALA H C*/


package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	
	String sheetName = "contacts";
	
	
	public ContactsPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		loginPage = new LoginPage();
		contactsPage = new ContactsPage();
		homePage = loginPage.login(prop.getProperty("email"), prop.getProperty("password"));
		contactsPage = homePage.clickOnContactsLink();
		Thread.sleep(3000);
	}
	
	@Test(priority=1)
	public void verifyContactsPageLabel() {
		Assert.assertTrue(contactsPage.verifyContactsLabel(),"contacts label is missing on the page");
		
	}
	
	@Test(priority=2)
	public void selectContactsTest() throws InterruptedException {
		contactsPage.selectContactsByName("test test 2");
		contactsPage.selectContactsByName("ui uiiii1");
	}
	
	@DataProvider
	public Object[][] getCRMTestData() {
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
		
	}
	
	@Test(priority=3,dataProvider="getCRMTestData")
	public void validateCreateNewContact(String firstName,String lastName, String email) throws InterruptedException {
		homePage.clickOnNewContact();
		//contactsPage.createNewContact("Tom", "Peter", "fth@gmail.com");
		contactsPage.createNewContact(firstName, lastName, email);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
