package yandex.test;

import com.sun.org.glassfish.gmbal.Description;
import static org.assertj.core.api.Assertions.*;
import org.junit.Test;
import yandex.disk.*;

public class YandexApiTest {

    @Test
    @Description("1.Создание и удаление папки")
    public void createDeleteRepositoryTest(){
        CreateRepositoryRequest.createRepository("Repository");
        DeleteRepositoryRequest.deleteRepository("Repository");
        FindResourseRequest.findResourse("Repository", 404);

    }
    @Test
    @Description("2.Создание папки, создание файла, удачение папки, удаление файла")
    public void createDeleteFileTest(){
        CreateRepositoryRequest.createRepository("Repository");
        CreateFileRequest.createFile("Repository", "file.txt");
        DeleteFileRequest.deleteFile("Repository", "file.txt");
        DeleteRepositoryRequest.deleteRepository("Repository");
    }
    @Test
    @Description("2.Поместить файл в корзину и востоновить")
    public void restoreBasketTest(){
        CreateRepositoryRequest.createRepository("Repository");
        CreateFileRequest.createFile("Repository", "file.txt");
        DeleteFileRequest.deleteFile("Repository", "file.txt");
        RestoreFileIFromBasketRequest.restoreFileFromBusket("file.txt");
        DeleteFileRequest.deleteFile("Repository", "file.txt");
        DeleteRepositoryRequest.deleteRepository("Repository");

    }
    @Test
    @Description("3.Получить информацию о диске и пользователе")
    public void getInfoTest() {
        CreateRepositoryRequest.createRepository("Repository");
        CreateFileRequest.createFile("Repository", "file1.txt");
        CreateFileRequest.createFile("Repository", "file2.txt");
        int sizeFile1 = FileSizeRequest.fileSizeRequest("Repository", "file1");
        int sizeFile2 = FileSizeRequest.fileSizeRequest("Repository", "file2");
        int sizeEmptyBasket = BastetSizeRequest.basketSizeRequest();
        DeleteFileRequest.deleteFile("Repository", "file1.txt");
        DeleteFileRequest.deleteFile("Repository", "file2.txt");
        int sizeFullBasket = BastetSizeRequest.basketSizeRequest();
        assertThat(sizeFullBasket).isEqualTo(sizeEmptyBasket + sizeFile1 + sizeFile2);
        RestoreFileIFromBasketRequest.restoreFileFromBusket("file1.txt");
        RestoreFileIFromBasketRequest.restoreFileFromBusket("file2.txt");
        DeleteFileRequest.deleteFile("Repository", "file1.txt");
        DeleteFileRequest.deleteFile("Repository", "file2.txt");
        DeleteRepositoryRequest.deleteRepository("Repository");
    }
    @Test
    @Description("4.Удаление репозитория с папкой и файлом")
    public void deleteRepositoryInRepositoryTest(){
        CreateRepositoryRequest.createRepository("Test");
        CreateRepositoryInRepositoryRequest.createRepositoryInRepository("Test","foo");
        CreateFileRequest.createFile("Test/foo","autotest");
        String typeDir = TypeRepositoryOrFileRequest.typeFile("repository");
        assertThat(typeDir).isEqualTo("dir");
        DeleteRepositoryRequest.deleteRepository("Test");
        FindResourseRequest.findResourse("Test", 404);
        FindResourseRequest.findResourse("Test/foo", 404);
        FindResourseRequest.findResourse("Test/foo/autotest", 404);

        }
        @Test
    @Description("5.Очистить корзину с папками и файлом")
    public void cleanBasketWithRepositoryAndFile(){
            CreateRepositoryRequest.createRepository("Test");
            CreateRepositoryInRepositoryRequest.createRepositoryInRepository("Test","foo");
            CreateFileRequest.createFile("Test/foo","autotest");
            DeleteRepositoryRequest.deleteRepository("Test");
            CleanBasketRequest.BasketClean();
            int basketSize = BastetSizeRequest.basketSizeRequest();
            assertThat(basketSize).isEqualTo(0);
            FindResourseRequest.findResourse("Test", 404);
            FindResourseRequest.findResourse("Test/foo", 404);
            FindResourseRequest.findResourse("Test/foo/autotest", 404);


        }

}
