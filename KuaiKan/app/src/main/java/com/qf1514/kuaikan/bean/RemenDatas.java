package com.qf1514.kuaikan.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/2/12.
 */
public class RemenDatas {


    private String code;
    private DataEntity data;
    private String message;

    public void setCode(String code) {
        this.code = code;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public DataEntity getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public static class DataEntity {


        private List<TopicsEntity> topics;

        public void setTopics(List<TopicsEntity> topics) {
            this.topics = topics;
        }

        public List<TopicsEntity> getTopics() {
            return topics;
        }

        public static class TopicsEntity {
            private String action;
            private String title;
            private String type;


            private List<TopicsEntity2> topics;

            public void setAction(String action) {
                this.action = action;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setTopics(List<TopicsEntity2> topics) {
                this.topics = topics;
            }

            public String getAction() {
                return action;
            }

            public String getTitle() {
                return title;
            }

            public String getType() {
                return type;
            }

            public List<TopicsEntity2> getTopics() {
                return topics;
            }

            public static class TopicsEntity2 {
                private String comics_count;
                private String cover_image_url;
                private String created_at;
                private String description;
                private String id;
                private boolean is_favourite;
                private String label_id;
                private String order;
                private String title;
                private String updated_at;
                /**
                 * avatar_url : http://i.kuaikanmanhua.com/image/150421/pvn0g8g8p.jpg-w180
                 * id : ​118037
                 * nickname : 卡里
                 * reg_type : weibo
                 */

                private UserEntity user;
                private String vertical_image_url;

                public void setComics_count(String comics_count) {
                    this.comics_count = comics_count;
                }

                public void setCover_image_url(String cover_image_url) {
                    this.cover_image_url = cover_image_url;
                }

                public void setCreated_at(String created_at) {
                    this.created_at = created_at;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public void setIs_favourite(boolean is_favourite) {
                    this.is_favourite = is_favourite;
                }

                public void setLabel_id(String label_id) {
                    this.label_id = label_id;
                }

                public void setOrder(String order) {
                    this.order = order;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public void setUpdated_at(String updated_at) {
                    this.updated_at = updated_at;
                }

                public void setUser(UserEntity user) {
                    this.user = user;
                }

                public void setVertical_image_url(String vertical_image_url) {
                    this.vertical_image_url = vertical_image_url;
                }

                public String getComics_count() {
                    return comics_count;
                }

                public String getCover_image_url() {
                    return cover_image_url;
                }

                public String getCreated_at() {
                    return created_at;
                }

                public String getDescription() {
                    return description;
                }

                public String getId() {
                    return id;
                }

                public boolean isIs_favourite() {
                    return is_favourite;
                }

                public String getLabel_id() {
                    return label_id;
                }

                public String getOrder() {
                    return order;
                }

                public String getTitle() {
                    return title;
                }

                public String getUpdated_at() {
                    return updated_at;
                }

                public UserEntity getUser() {
                    return user;
                }

                public String getVertical_image_url() {
                    return vertical_image_url;
                }

                public static class UserEntity {
                    private String avatar_url;
                    private String id;
                    private String nickname;
                    private String reg_type;

                    public void setAvatar_url(String avatar_url) {
                        this.avatar_url = avatar_url;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public void setNickname(String nickname) {
                        this.nickname = nickname;
                    }

                    public void setReg_type(String reg_type) {
                        this.reg_type = reg_type;
                    }

                    public String getAvatar_url() {
                        return avatar_url;
                    }

                    public String getId() {
                        return id;
                    }

                    public String getNickname() {
                        return nickname;
                    }

                    public String getReg_type() {
                        return reg_type;
                    }
                }
            }
        }
    }
}
