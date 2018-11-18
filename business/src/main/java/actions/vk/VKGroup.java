package actions.vk;

/**
 * Внутреннее представление клиента как сообщество (группа)
 * Created by Виктор on 28.05.2017.
 */
public class VKGroup {
    private String name;
    private String urlPhoto;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }
}
