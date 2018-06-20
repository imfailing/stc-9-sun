package ru.innopolis.stc9.sun.academy.entity;

public class Mark {
    private Integer id;

    private Lesson lesson;

    private Integer lessonId;

    private User user;

    private Integer userId;

    private Integer value;

    public Mark() {

    }

    public Mark(Integer id) {
        this.id = id;
    }

    public Mark(Integer id, Integer lessonId, Integer userId, Integer value) {
        this.id = id;
        this.lessonId = lessonId;
        this.userId = userId;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
        if (lesson != null) {
            this.lessonId = lesson.getId();
        } else {
            this.lessonId = null;
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        if (user != null) {
            this.userId = user.getId();
        } else {
            this.userId = null;
        }
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
