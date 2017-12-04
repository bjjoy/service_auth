package cn.bjjoy.service.auth.dto;

/**
 * Created by bjjoy on 2017/11/02.
 */
public class UserRoleDto {

    /**
     * Role表角色id
     */
    private Integer id;

    /**
     * UserRole表角色id
     */
    private Integer roleId;

    /**
     * UserRole表用户uuid
     */
    private String userUuid;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 英文名称
     */
    private String enname;

    /**
     * 对应用户是否选中该角色(0否，1是)
     * @return
     */
    private Integer isSelect = 0;

    private String permission;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnname() {
        return enname;
    }

    public void setEnname(String enname) {
        this.enname = enname;
    }


    public Integer getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(Integer isSelect) {
        this.isSelect = isSelect;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
