package tests;

import application.enums.*;
import application.enums.Parameters;
import application.model.CommentModel;
import application.model.PostModel;
import application.model.User;
import application.pageObject.FeedPage;
import application.pageObject.MainePage;
import application.pageObject.MyProfilePage;
import application.pageObject.ProfilePageNoAuth;
import application.request.Attachments;
import application.request.Likes;
import application.request.Wall;
import application.serializableObject.PhotoParam;
import application.serializableObject.WallPost;
import framework.browsers.BrowserActions;
import framework.files.EqualsPicture;
import framework.files.FileManager;
import framework.logger.MyLogger;
import framework.utils.PropertiesManager;
import framework.utils.RandomGeneration;

import org.testng.annotations.*;
import steps.SaveFile;

import static org.testng.Assert.*;

public class TestAPIVK {

    private static MyLogger LOGGER = new MyLogger();

    @BeforeTest
    private void setUp() {
        PropertiesManager.loadProperties("config");
    }

    @BeforeMethod
    private void openPage() {
        FileManager.deleteDownloadFile(PropertiesManager.getProperties("pathImg2"));
        BrowserActions.openPage();
    }

    @AfterMethod
    private void afterTest() {
        BrowserActions.quitBrowser();
    }

    @Test
    public void testCase1() {
        MainePage manePage = new MainePage();
        LOGGER.step("1", "UI - go to the page https://vk.com/");

        LOGGER.step("2", "UI - users authorization");
        manePage.authorization(PropertiesManager.getProperties("login1"), PropertiesManager.getProperties("password1"));
        FeedPage feedPage = new FeedPage();
        User user = new User(feedPage.getSideBar().getUserID());
        user.setToken("token1");

        assertTrue(feedPage.isPostField(), "feed page is not open ");

        LOGGER.step("3", "UI - go to my profile page ");
        feedPage.getSideBar().clickElementMenu(MenuCategory.PROFILE);
        MyProfilePage myProfilePage = new MyProfilePage();
        assertTrue(myProfilePage.isMyProfilePage(), "is not profile page");

        LOGGER.step("4", "API - create a post with random text on the wall and get id from the response");
        PostModel postModel = new PostModel(RandomGeneration.randomString(15), user, "newPost");

        LOGGER.step("5", "UI - a post appeared on the wall with the right text from the correct user");
        postModel.setId(Wall.wallPost(postModel, user).getPostId());
        assertEquals(myProfilePage.getPost().getText(postModel), postModel.getText(), "text don't equals");
        assertEquals(myProfilePage.getPost().getAuthorID(postModel), postModel.getUser().getUserId(), "user don't equals");

        LOGGER.step("6", "API - Edit a record through an API request - change the text and add (upload) any image");

        PostModel editedPostModel = new PostModel("editPost", postModel.getID(), RandomGeneration.randomString(20), user);
        PhotoParam photo = SaveFile.savePhoto(editedPostModel, PropertiesManager.getProperties("pathImg"), Parameters.PHOTO, user);
        editedPostModel.setAttachments(Attachments.create().add(Attachment.PHOTO, photo.getId()));
        Wall.wallEdit(editedPostModel, user);

        LOGGER.step("7", "UI - the message text has changed and the uploaded picture has been added (make sure that the pictures are the same)");

        assertEquals(editedPostModel.getText(), myProfilePage.getPost().getText(editedPostModel), "text don't equals ");
        myProfilePage.getPost().clickPhoto(postModel, photo);

        FileManager.saveImg(myProfilePage.getPost().getLinkPhoto(editedPostModel, photo), PropertiesManager.getProperties("pathImg2"));
        assertTrue(EqualsPicture.compareImages(PropertiesManager.getProperties("pathImg"), PropertiesManager.getProperties("pathImg2")) == 100,
                "picture don't equals");
        myProfilePage.getPost().clickCloseBtn();

        LOGGER.step("8", "API - Using an API request add a comment to a post with random text");
        CommentModel commentModel = new CommentModel(editedPostModel, Wall.wallCreateComment(editedPostModel, user).getCommentID(), user);

        LOGGER.step("9", "UI - make sure that the comment from the correct user is added to the desired entry");
        myProfilePage.getPost().clickAllComment(editedPostModel);

        assertTrue(myProfilePage.getPost().getRepliesPostID(commentModel).contains(commentModel.getPostModel().getID().toString()), "comment refers to another post");
        assertEquals(commentModel.getUser().getUserId(), myProfilePage.getPost().getCommentAuthor(commentModel), "author comment is not match ");
        assertEquals(editedPostModel.getText(), myProfilePage.getPost().getTextComment(commentModel), "test in comment don't equals");

        LOGGER.step("10", "UI - Through UI leave a Like on the record");
        myProfilePage.getPost().clickLike(editedPostModel);

        LOGGER.step("11", "API - Through a request to the API, make sure that the record has a like from the correct user");
        assertEquals(Likes.likesIsLiked(editedPostModel, user, user).getLiked(), Like.LIKED.getTitle(), "not like from correct user");

        LOGGER.step("12", "API - Through an API request, delete the created record");
        Wall.wallDelete(editedPostModel, user);

        LOGGER.step("13", "UI - Without refreshing the page make sure the record is deleted");
        assertFalse(myProfilePage.getPost().isUnShownPost(editedPostModel), " post is not delete");
    }

    @Test
    public void testCase2() {
        LOGGER.step("1", "[UI] go to https://vk.com/");
        MainePage manePage = new MainePage();

        LOGGER.step("2", "[UI] User1 authorization");
        manePage.authorization(PropertiesManager.getProperties("login1"), PropertiesManager.getProperties("password1"));

        LOGGER.step("3", "[UI] go to MyProfile page");
        FeedPage feedPage = new FeedPage();
        feedPage.getSideBar().clickElementMenu(MenuCategory.PROFILE);
        MyProfilePage myProfilePage = new MyProfilePage();

        LOGGER.step("4", "[UI] get User1 page address ");
        String urlUser1 = myProfilePage.getSideBar().getElementMenu(MenuCategory.PROFILE).getAttribute("href");

        LOGGER.step("5", "[UI] get id User1 ");
        User user1 = new User(myProfilePage.getSideBar().getUserID());
        user1.setToken("token1");

        LOGGER.step("6", "[API] Using an API request, create a post with randomly generated text on the wall " +
                "and get the post id from the response. The entry must be created from User 1, on the User 1 page.");
        PostModel postModel = new PostModel(RandomGeneration.randomString(20), user1, "post1");
        WallPost post1 = Wall.wallPost(postModel, user1);
        postModel.setId(post1.getPostId());

        LOGGER.step("7", "use [UI] like wall post");
        myProfilePage.getPost().clickLike(postModel);

        LOGGER.step("8", "logout");
        myProfilePage.getHeader().getTopProfile().getCategory(TopCategory.LOGOUT).clickElement();

        LOGGER.step("9", "go to https://vk.com/");
        MainePage manePage2 = new MainePage();

        LOGGER.step("10", "[UI] User2 authorization");
        manePage2.authorization(PropertiesManager.getProperties("login2"), PropertiesManager.getProperties("password2"));
        MyProfilePage u2ProfilePage = new MyProfilePage();

        LOGGER.step("11", "go to the User1 page");
        BrowserActions.goToPage(urlUser1);
        MyProfilePage u1ProfilePage = new MyProfilePage();
        User user2 = new User(u2ProfilePage.getSideBar().getUserID());
        user2.setToken("token2");

        LOGGER.step("12", "Make sure that the previously created record is displayed with the correct test " +
                "and that the id matches the one received in the response from the API.");
        assertEquals(postModel.getText(), u1ProfilePage.getPost().getText(postModel), "text don't equals");
        assertTrue(u1ProfilePage.getPost().isDisplayed(postModel), "post not found");

        LOGGER.step("13", "[UI] like post User1");
        u1ProfilePage.getPost().clickLike(postModel);

        LOGGER.step("14", "Through a request to the API, make sure that the record has likes from User1 and User2");
        try {
            LOGGER.info("Sleeping...");
            Thread.sleep((long) 25000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(Likes.likesIsLiked(postModel, user1, user1).getLiked(), Like.LIKED.getTitle(), "is not like from User1");
        assertEquals(Likes.likesIsLiked(postModel, user2, user1).getLiked(), Like.LIKED.getTitle(), "is not like from User2");
    }

    @Test
    public void testCase3() {
        LOGGER.step("1", "[UI] go to https://vk.com/");
        MainePage manePage = new MainePage();

        LOGGER.step("2", "[UI] User1 authorization");
        manePage.authorization(PropertiesManager.getProperties("login1"), PropertiesManager.getProperties("password1"));

        LOGGER.step("3", "[UI] go to MyProfile page");
        FeedPage feedPage = new FeedPage();
        feedPage.getSideBar().clickElementMenu(MenuCategory.PROFILE);
        MyProfilePage myProfilePage = new MyProfilePage();

        LOGGER.step("4", "[UI] get User1 page address ");
        String urlUser1 = myProfilePage.getSideBar().getElementMenu(MenuCategory.PROFILE).getAttribute("href");

        LOGGER.step("5", "[UI] get id User1 ");
        User user1 = new User(myProfilePage.getSideBar().getUserID());
        user1.setToken("token1");

        LOGGER.step("6", "[API] Using an API request, create a post with randomly generated text on the wall " +
                " and get the post id from the response. The entry must be created from User 1, on the User 1 page.");
        PostModel postModel = new PostModel(RandomGeneration.randomString(20), user1, "post1");
        WallPost post1 = Wall.wallPost(postModel, user1);
        postModel.setId(post1.getPostId());

        LOGGER.step("7", "[UI] logout");
        myProfilePage.getHeader().getTopProfile().getCategory(TopCategory.LOGOUT).clickElement();

        LOGGER.step("8", "[UI] go to https://vk.com/");
        MainePage manePage2 = new MainePage();

        LOGGER.step("9", "[UI] go to the User1 page");
        BrowserActions.goToPage(urlUser1);
        ProfilePageNoAuth u1ProfilePage = new ProfilePageNoAuth();

        LOGGER.step("10", "[UI] Make sure that the previously created record is displayed with the correct test " +
                "and that the id matches the one received in the response from the API.");
        assertEquals(postModel.getText(), u1ProfilePage.getPost().getText(postModel), "text don't equals");
        assertTrue(u1ProfilePage.getPost().isDisplayed(postModel), "post not found");

        LOGGER.step("11", "[UI] Verify that the site login form is displayed.");
        assertTrue(u1ProfilePage.getAuthorizationForm().getForm().isDisplayed());

    }

    @Test
    public void testCase4() {
        LOGGER.step("1", "[UI] go to https://vk.com/");
        MainePage manePage = new MainePage();

        LOGGER.step("2", "[UI] User1 authorization");
        manePage.authorization(PropertiesManager.getProperties("login1"), PropertiesManager.getProperties("password1"));

        LOGGER.step("3", "[UI] go to MyProfile page");
        FeedPage feedPage = new FeedPage();
        feedPage.getSideBar().clickElementMenu(MenuCategory.PROFILE);
        User user1 = new User(feedPage.getSideBar().getUserID());
        user1.setToken("token1");
        MyProfilePage myProfilePage = new MyProfilePage();

        LOGGER.step("4", "[API] Using an API request, create a post with randomly generated text on the wall " +
                " and get the post id from the response.");
        PostModel postModel = new PostModel(RandomGeneration.randomString(15), user1, "post1");
        WallPost post1 = Wall.wallPost(postModel, user1);
        postModel.setId(post1.getPostId());

        LOGGER.step("5", "[API] - Edit a record through an API request - change the text and add (upload) any file");

        PostModel editedPostModel = new PostModel("editPost", postModel.getID(), RandomGeneration.randomString(25), user1);
        Attachments attachments = Attachments.create().add(Attachment.DOC, SaveFile.saveFile(editedPostModel,
                PropertiesManager.getProperties("pathFile") + PropertiesManager.getProperties("fileName"), Parameters.FILE, user1));
        editedPostModel.setAttachments(attachments);
        Wall.wallEdit(editedPostModel, user1);

        LOGGER.step("6", "[UI] Without refreshing the page, make sure that the message text has changed " +
                "and the downloaded file has been added (compare names only)");
        assertEquals(myProfilePage.getPost().getText(editedPostModel), editedPostModel.getText(), "text doesn't equals");
        assertEquals(myProfilePage.getPost().getDocName(editedPostModel), PropertiesManager.getProperties("fileName"), "fileName doesn't equals");

        LOGGER.step("7", "API - Using an API request add a comment to a post with random text");
        CommentModel commentModel = new CommentModel(editedPostModel, Wall.wallCreateComment(editedPostModel, user1).getCommentID(), user1);

        LOGGER.step("9", "UI - make sure that the comment from the correct user is added to the desired entry");
        myProfilePage.getPost().clickAllComment(editedPostModel);
        assertTrue(myProfilePage.getPost().getRepliesPostID(commentModel).contains(commentModel.getPostModel().getID().toString()), "comment refers to another post");
        assertEquals(commentModel.getUser().getUserId(), myProfilePage.getPost().getCommentAuthor(commentModel), "author comment is not match ");
        assertEquals(editedPostModel.getText(), myProfilePage.getPost().getTextComment(commentModel), "test in comment don't equals");

        LOGGER.step("9", "UI - Through UI leave a Like on the record");
        myProfilePage.getPost().clickLike(editedPostModel);

        LOGGER.step("10", "API - Through a request to the API, make sure that the record has a like from the correct user");
        assertEquals(Likes.likesIsLiked(editedPostModel, user1, user1).getLiked(), Like.LIKED.getTitle(), "not like from correct user");

        LOGGER.step("11", "API - Through an API request, delete the created record");
        Wall.wallDelete(editedPostModel, user1);

        LOGGER.step("12", "UI - Without refreshing the page make sure the record is deleted");
        assertFalse(myProfilePage.getPost().isUnShownPost(editedPostModel), " post is not delete");
    }

    @DataProvider
    public Object[][] getData() {
        return new Object[][]{{"pathPNG", "pathImg2", 2}, {"pathImg", "pathImg2", 100}};
    }

    @Test(dataProvider = "getData")
    public void testCase5(String path1, String path2, float interest) {

        LOGGER.step("1", "[UI] go to https://vk.com/");
        MainePage manePage = new MainePage();

        LOGGER.step("2", "[UI] User1 authorization");
        manePage.authorization(PropertiesManager.getProperties("login2"), PropertiesManager.getProperties("password2"));

        LOGGER.step("3", "[UI] go to MyProfile page");
        FeedPage feedPage = new FeedPage();
        feedPage.getSideBar().clickElementMenu(MenuCategory.PROFILE);
        User user2 = new User(feedPage.getSideBar().getUserID());
        user2.setToken("token2");
        MyProfilePage myProfilePage = new MyProfilePage();

        LOGGER.step("4", "[API] Using an API request, create a post with randomly generated text on the wall " +
                " and get the post id from the response.");
        PostModel postModel = new PostModel(RandomGeneration.randomString(15), user2, "post1");
        PhotoParam photo = SaveFile.savePhoto(postModel, PropertiesManager.getProperties(path1), Parameters.PHOTO, user2);
        postModel.setAttachments(Attachments.create().add(Attachment.PHOTO, photo.getId()));
        WallPost post1 = Wall.wallPost(postModel, user2);
        postModel.setId(post1.getPostId());

        LOGGER.step("5", "UI - the message text has changed and the uploaded picture has been added (make sure that the pictures are the same)");
        assertEquals(postModel.getText(), myProfilePage.getPost().getText(postModel), "text don't equals ");
        myProfilePage.getPost().clickPhoto(postModel, photo);
        FileManager.saveImg(myProfilePage.getPost().getLinkPhoto(postModel, photo), PropertiesManager.getProperties(path2));

        assertTrue(EqualsPicture.compareImages(PropertiesManager.getProperties(path1), PropertiesManager.getProperties(path2)) >= interest,
                "picture don't equals");
        myProfilePage.getPost().clickCloseBtn();
    }

}
