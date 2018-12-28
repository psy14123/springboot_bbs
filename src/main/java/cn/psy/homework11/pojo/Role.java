package cn.psy.homework11.pojo;

/**
 * 角色相关
 */
public class Role {
   private  int id;
    private String role;//角色名


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role=" + role +
                '}';
    }
}
