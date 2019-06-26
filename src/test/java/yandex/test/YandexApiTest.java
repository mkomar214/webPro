package yandex.test;

import com.sun.org.glassfish.gmbal.Description;
import static org.assertj.core.api.Assertions.*;
import org.junit.Test;
import yandex.disk.*;

import java.util.concurrent.TimeUnit;

public class YandexApiTest {

    @Test
    @Description("1.Создание и удаление папки")
    public void createDeleteRepositoryTest(){
        CreateRepositoryRequest.createRepository("Repo1");
        DeleteRepositoryRequest.deleteRepository("Repo1");
        FindResourseRequest.findResourse("Repo1", 404);

    }
    @Test
    @Description("2.Создание папки, создание файла, удачение папки, удаление файла")
    public void createDeleteFileTest(){
        CreateRepositoryRequest.createRepository("Repo2");
        CreateFileRequest.createFile("Repo2", "Море.jpg");
        DeleteFileRequest.deleteFile("Repo2", "Море.jpg");
        DeleteRepositoryRequest.deleteRepository("Repo2");
    }
    @Test
    @Description("3.Поместить файл в корзину и востоновить")
    public void restoreBasketTest() throws InterruptedException {
        CreateRepositoryRequest.createRepository("Repo3");
        CreateFileRequest.createFile("Repo3", "Море.jpg");
        DeleteFileRequest.deleteFile("Repo3", "Море.jpg");
        RestoreFileIFromBasketRequest.restoreFileFromBusket("Море.jpg");
        TimeUnit.SECONDS.sleep(5);
        DeleteFileRequest.deleteFile("Repo3", "Море.jpg");
        DeleteRepositoryRequest.deleteRepository("Repo3");
    }
    @Test
    @Description("4.Получить информацию о диске и пользователе")
    public void getInfoTest() throws InterruptedException {
        CreateRepositoryRequest.createRepository("Repo4");
        CreateFileRequest.createFile("Repo4", "Море.jpg");
        CreateFileRequest.createFile("Repo4", "Мишки.jpg");
        int sizeFile1 = FileSizeRequest.fileSizeRequest("Repo4", "Море.jpg");
        int sizeFile2 = FileSizeRequest.fileSizeRequest("Repo4", "Мишки.jpg");
        int sizeEmptyBasket = BastetSizeRequest.basketSizeRequest();
        DeleteFileRequest.deleteFile("Repo4", "Море.jpg");
        DeleteFileRequest.deleteFile("Repo4", "Мишки.jpg");
        int sizeFullBasket = BastetSizeRequest.basketSizeRequest();
        assertThat(sizeFullBasket).isEqualTo(sizeEmptyBasket + sizeFile1 + sizeFile2);
        RestoreFileIFromBasketRequest.restoreFileFromBusket("Море.jpg");
        RestoreFileIFromBasketRequest.restoreFileFromBusket("Мишки.jpg");
        TimeUnit.SECONDS.sleep(5);
        DeleteFileRequest.deleteFile("Repo4", "Море.jpg");
        DeleteFileRequest.deleteFile("Repo4", "Мишки.jpg");
        DeleteRepositoryRequest.deleteRepository("Repo4");
    }
    @Test
    @Description("5.Удаление репозитория с папкой и файлом")
    public void deleteRepositoryInRepositoryTest() throws InterruptedException {
        CreateRepositoryRequest.createRepository("Test");
        CreateRepositoryInRepositoryRequest.createRepositoryInRepository("Test","foo");
        CreateFileRequest.createFile("Test/foo","Море.jpg");
        String typeDir = TypeRepositoryOrFileRequest.typeFile("Test");
        assertThat(typeDir).isEqualTo("dir");
        DeleteRepositoryRequest.deleteFullRepository("Test");
        TimeUnit.SECONDS.sleep(3);
        FindResourseRequest.findResourse("Test", 404);
        FindResourseRequest.findResourse("Test/foo", 404);
        FindResourseRequest.findResourse("Test/foo/Море.jpg", 404);
        }

    @Test
    @Description("6.Очистить корзину с папками и файлом")
    public void cleanBasketWithRepositoryAndFile() throws InterruptedException {
            CreateRepositoryRequest.createRepository("Test");
            CreateRepositoryInRepositoryRequest.createRepositoryInRepository("Test","foo");
            CreateFileRequest.createFile("Test/foo","Море.jpg");
            DeleteRepositoryRequest.deleteFullRepository("Test");
            TimeUnit.SECONDS.sleep(3);
            CleanBasketRequest.BasketClean();
            TimeUnit.SECONDS.sleep(10);
            int basketSize = BastetSizeRequest.basketSizeRequest();
            assertThat(basketSize).isEqualTo(0);
            FindResourseRequest.findResourse("Test", 404);
            FindResourseRequest.findResourse("Test/foo", 404);
            FindResourseRequest.findResourse("Test/foo/Море.jpg", 404);
        }

}
