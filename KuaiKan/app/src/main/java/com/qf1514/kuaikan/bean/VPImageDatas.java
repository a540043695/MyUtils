package com.qf1514.kuaikan.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/2/11.
 */
public class VPImageDatas {

    /**
     * code : ​200
     * data : {"banner_group":[{"pic":"http://i.kuaikanmanhua.com/image/160208/ko3t033kn.webp-w640","title":"","type":"\u200b2","value":"311"},{"pic":"http://i.kuaikanmanhua.com/image/160209/bzawusaw1.webp-w640","title":"","type":"\u200b3","value":"9187"},{"pic":"http://i.kuaikanmanhua.com/image/160209/xzn77zo3k.webp-w640","title":"","type":"\u200b3","value":"9342"},{"pic":"http://i.kuaikanmanhua.com/image/160209/fufnh8q7j.webp-w640","title":"","type":"\u200b3","value":"9031"},{"pic":"http://i.kuaikanmanhua.com/image/160209/452xrase5.webp-w640","title":"","type":"\u200b3","value":"8926"},{"pic":"http://i.kuaikanmanhua.com/image/160206/a2vun2v1t.webp-w640","title":"快看猴年红包福利来啦！","type":"\u200b1","value":"http://www.kuaikanmanhua.com/hongbao/home"}]}
     * message : OK
     */

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
        /**
         * pic : http://i.kuaikanmanhua.com/image/160208/ko3t033kn.webp-w640
         * title :
         * type : ​2
         * value : 311
         */

        private List<BannerGroupEntity> banner_group;

        public void setBanner_group(List<BannerGroupEntity> banner_group) {
            this.banner_group = banner_group;
        }

        public List<BannerGroupEntity> getBanner_group() {
            return banner_group;
        }

        public static class BannerGroupEntity {
            private String pic;
            private String title;
            private String type;
            private String value;

            public void setPic(String pic) {
                this.pic = pic;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getPic() {
                return pic;
            }

            public String getTitle() {
                return title;
            }

            public String getType() {
                return type;
            }

            public String getValue() {
                return value;
            }
        }
    }
}
