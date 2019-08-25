import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.util.Properties;

public class CreatePublicGist {
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
    public void createPubGist() throws Exception {
        GistAutomate.clickElementXpath("//a[@data-ga-click='Header, go to new gist, text:new gist']");
        GistAutomate.inputText("//div[@id='gists']//input[@name='gist[description]']", "Test Creating Gist");
        GistAutomate.inputText("//input[@type='text' and @aria-label='Filename including extension…']", "Auto");
        GistAutomate.clickElementXpath("//div[@role='presentation']");
        GistAutomate.inputText("//div[@class='CodeMirror-code' and @contenteditable='true']", "this is automation");
        GistAutomate.clickElementXpath("//button[@type='submit' and @value='1']");
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
