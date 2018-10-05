package ru.p4b.dev.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.p4b.dev.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
     app.getContactHelper().createContact(new ContactData("Elena", "Kildishova", "+79139507792", "elenanov@ngs.ru", "[none]"), true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.acceptNextAlert = true;
    app.getContactHelper().deleteSelectedContacts();
    app.wd.switchTo().alert().accept();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    for (int i = 0; i < after.size(); i++) {
      Assert.assertEquals(before.get(i), after.get(i));
    }
    app.logout();
  }

}
