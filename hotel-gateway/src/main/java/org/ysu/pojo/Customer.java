package org.ysu.pojo;

public class Customer {
    private Integer id;

    private String name;

    private Integer sex;

    private String tel;

    private String email;

    private Integer integra;

    private Integer level;

    private String lastCtime;

    private String lastRtime;

    private String note;

    private String password;

    public Customer(Integer id, String name, Integer sex, String tel, String email, Integer integra, Integer level, String lastCtime, String lastRtime, String note, String password) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.tel = tel;
        this.email = email;
        this.integra = integra;
        this.level = level;
        this.lastCtime = lastCtime;
        this.lastRtime = lastRtime;
        this.note = note;
        this.password = password;
    }

    public Customer() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getIntegra() {
        return integra;
    }

    public void setIntegra(Integer integra) {
        this.integra = integra;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getLastCtime() {
        return lastCtime;
    }

    public void setLastCtime(String lastCtime) {
        this.lastCtime = lastCtime == null ? null : lastCtime.trim();
    }

    public String getLastRtime() {
        return lastRtime;
    }

    public void setLastRtime(String lastRtime) {
        this.lastRtime = lastRtime == null ? null : lastRtime.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}