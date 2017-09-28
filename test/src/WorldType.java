public class WorldType {

    //值
    private long value;
    //类型
    private String type;
    //字
    private String world;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void tostring(){
        System.out.println("{"+type+"}"+"  "+world);
    }
}
