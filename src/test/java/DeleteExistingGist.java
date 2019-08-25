import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.util.Properties;

public class DeleteExistingGist {
    Properties props;
    @BeforeClass
    public void setUp() throws Exception {
        props = new Properties();
        FileInputStream fis = new FileInputStream("src//test//Resource//Variable.properties");
        props.load(fis);

        GistAutomate.openBrowser(props.getProperty("site"));
        signIn();
    }

    @Test
    public void deleteGist() throws Exception {
        GistAutomate.clickElementXpath("//summary[@data-ga-click='Header, show menu, icon:avatar']");
        GistAutomate.clickElementXpath("//a[@data-ga-click='Header, go to your gists, text:your gists']");
        GistAutomate.clickElementXpath("//div[@class='gist-snippet']//strong[1]");
        GistAutomate.clickElementXpath("//button[@type='submit' and @aria-label='Delete this Gist']");
        GistAutomate.acceptAlert();
    }

    @AfterClass
    public void closeBrowser(){
        GistAutomate.closeBrowser();
    }



    public void signIn() throws Exception {
        GistAutomate.clickElementXpath("//a[@data-ga-click='Header, sign in']");
        GistAutomate.inputText("//input[@id='login_field']",props.getProperty("email"));
        GistAutomate.inputText("//input[@id='password']",props.getProperty("pass"));
        GistAutomate.clickElementXpath("//input[@type='submit']");
    }
}
