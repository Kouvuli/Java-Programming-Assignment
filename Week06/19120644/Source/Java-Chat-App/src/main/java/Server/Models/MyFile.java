package Server.Models;

public class MyFile {
    private int id;
    private byte[] name;
    private int nameLength;
    private byte[] data;
    private int dataLength;
    private String fileExtension;
    private byte[] clientUsername;
    private int clientUsernameLength;

    public MyFile(int id, byte[] name, int nameLength, byte[] data, int dataLength, String fileExtension, byte[] clientUsername, int clientUsernameLength) {
        this.id = id;
        this.name = name;
        this.nameLength = nameLength;
        this.data = data;
        this.dataLength = dataLength;
        this.fileExtension = fileExtension;
        this.clientUsername = clientUsername;
        this.clientUsernameLength = clientUsernameLength;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getName() {
        return name;
    }

    public void setName(byte[] name) {
        this.name = name;
    }

    public int getNameLength() {
        return nameLength;
    }

    public void setNameLength(int nameLength) {
        this.nameLength = nameLength;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getDataLength() {
        return dataLength;
    }

    public void setDataLength(int dataLength) {
        this.dataLength = dataLength;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public byte[] getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(byte[] clientUsername) {
        this.clientUsername = clientUsername;
    }

    public int getClientUsernameLength() {
        return clientUsernameLength;
    }

    public void setClientUsernameLength(int clientUsernameLength) {
        this.clientUsernameLength = clientUsernameLength;
    }
}
