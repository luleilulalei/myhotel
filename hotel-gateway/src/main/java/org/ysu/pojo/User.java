package org.ysu.pojo;

public class User {
    private Integer id;

    private String login;

    private String name;

    private String password;

    private Integer permissions;

    private String note;

    public User(Integer id, String login, String name, String password, Integer permissions, String note) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.password = password;
        this.permissions = permissions;
        this.note = note;
    }

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login == null ? null : login.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getPermissions() {
        return permissions;
    }

    public void setPermissions(Integer permissions) {
        this.permissions = permissions;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}