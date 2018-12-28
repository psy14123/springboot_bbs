package cn.psy.homework11.pojo;

/**
 * 用户权限相关
 */
public class Power {

    private int add_p;//添加文章的权限

    private int delete_p;//删除文章的权限

    private int update_p;//更改编辑文章的权限

    private int reply;//评论的权限

    private int see;//查看文章的权限

    private int role_id;//角色的id

    private int manage;//管理的权限



    public int getReply() {
        return reply;
    }

    public void setReply(int reply) {
        this.reply = reply;
    }

    public int getSee() {
        return see;
    }

    public void setSee(int see) {
        this.see = see;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getAdd_p() {
        return add_p;
    }

    public void setAdd_p(int add_p) {
        this.add_p = add_p;
    }

    public int getDelete_p() {
        return delete_p;
    }

    public void setDelete_p(int delete_p) {
        this.delete_p = delete_p;
    }

    public int getUpdate_p() {
        return update_p;
    }

    public void setUpdate_p(int update_p) {
        this.update_p = update_p;
    }

    public int getManage() {
        return manage;
    }

    public void setManage(int manage) {
        this.manage = manage;
    }

    @Override
    public String toString() {
        return "Power{" +
                "add_p=" + add_p +
                ", delete_p=" + delete_p +
                ", update_p=" + update_p +
                ", reply=" + reply +
                ", see=" + see +
                ", role_id=" + role_id +
                ", manage=" + manage +
                '}';
    }
}
