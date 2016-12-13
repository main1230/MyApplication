package com.zzl.cn.bean;

import java.util.List;

/**
 * Created by zhangzl
 * desc:
 * date: 2016/11/15.
 */

public class RementuijianResult {

    /**
     * ret : 200
     * msg : 查询成功
     * rows : [{"id":"G1517B7B02CC513FBB7E572D","fiveprice":13.6,"sales":662,"name":"斯太尔（超光）","oneprice":7.8,"picone":"http://qplg.geekapp.cn:8080/AutoPartShop/upload/hengfa/image/20151207/ihvo9lcv_532.jpg"},{"id":"G1517F33CDE3633FD1EE6777","fiveprice":13.6,"sales":495,"name":"奔驰、德龙、红岩（超光）","oneprice":7.8,"picone":"http://qplg.geekapp.cn:8080/AutoPartShop/upload/hengfa/image/20151208/ihwpdb76_809.jpg"},{"id":"G1517F3969CA653FE847720D","fiveprice":12,"sales":1,"name":"斯太尔（黑磷化）","oneprice":7,"picone":"http://qplg.geekapp.cn:8080/AutoPartShop/upload/hengfa/image/20151208/ihwpm6nx_703.jpg"},{"id":"G1517F7209AE733FCBD0499D","fiveprice":12,"sales":0,"name":"奔驰、德龙、红岩（黑磷化）","oneprice":7,"picone":"http://qplg.geekapp.cn:8080/AutoPartShop/upload/hengfa/image/20151208/ihwrv619_845.jpg"},{"id":"G1517F76EFD3753FDF3E08F6","fiveprice":16.4,"sales":0,"name":"奔驰、德龙、红岩（镀镍）","oneprice":10.2,"picone":"http://qplg.geekapp.cn:8080/AutoPartShop/upload/hengfa/image/20151208/ihws1oko_363.jpg"},{"id":"G1517F7AD22A773FDD038F19","fiveprice":16.4,"sales":180,"name":"斯太尔（镀镍）","oneprice":9.5,"picone":"http://qplg.geekapp.cn:8080/AutoPartShop/upload/hengfa/image/20151208/ihws7ewi_439.jpg"},{"id":"G1517F81D76B793FD97D5CE0","fiveprice":19.6,"sales":330,"name":"斯太尔加长（镀镍）","oneprice":11,"picone":"http://qplg.geekapp.cn:8080/AutoPartShop/upload/hengfa/image/20151208/ihwsgary_717.jpg"},{"id":"G1517F85AD657B3FE728D8C6","fiveprice":19.6,"sales":270,"name":"奔驰、德龙、红岩（镀镍）加长","oneprice":11,"picone":"http://qplg.geekapp.cn:8080/AutoPartShop/upload/hengfa/image/20151208/ihwsmcgn_582.jpg"},{"id":"G1517F8ADAB17D3FE7E3AC53","fiveprice":21.6,"sales":504,"name":"柳特、挂车（后轮镀镍）加长","oneprice":12,"picone":"http://qplg.geekapp.cn:8080/AutoPartShop/upload/hengfa/image/20151208/ihwstqky_988.jpg"},{"id":"G151DD5F603FF3FEC4E59BF0","fiveprice":18,"sales":1,"name":"140后右","oneprice":10,"picone":"http://qplg.geekapp.cn:8080/AutoPartShop/upload/hengfa/image/20151226/iimu5gmt_334.jpg"}]
     */

    private int ret;
    private String msg;
    private List<RementuijianEntity> rows;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<RementuijianEntity> getRows() {
        return rows;
    }

    public void setRows(List<RementuijianEntity> rows) {
        this.rows = rows;
    }

    public static class RementuijianEntity {
        /**
         * id : G1517B7B02CC513FBB7E572D
         * fiveprice : 13.6
         * sales : 662
         * name : 斯太尔（超光）
         * oneprice : 7.8
         * picone : http://qplg.geekapp.cn:8080/AutoPartShop/upload/hengfa/image/20151207/ihvo9lcv_532.jpg
         */

        private String id;
        private double fiveprice;
        private int sales;
        private String name;
        private double oneprice;
        private String picone;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public double getFiveprice() {
            return fiveprice;
        }

        public void setFiveprice(double fiveprice) {
            this.fiveprice = fiveprice;
        }

        public int getSales() {
            return sales;
        }

        public void setSales(int sales) {
            this.sales = sales;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getOneprice() {
            return oneprice;
        }

        public void setOneprice(double oneprice) {
            this.oneprice = oneprice;
        }

        public String getPicone() {
            return picone;
        }

        public void setPicone(String picone) {
            this.picone = picone;
        }
    }
}
