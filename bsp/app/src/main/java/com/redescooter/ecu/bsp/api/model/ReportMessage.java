package com.redescooter.ecu.bsp.api.model;

    // TODO: 2019/4/16
    public class ReportMessage {
    /**
     * 车锁状态
     */
    private boolean lockStatus;

    /**
     * 后备箱锁状态
     */
    private boolean trunkLockStatus ;

    /**
     * 后备箱温度   单位 摄氏度
     */
    private float trunkTemperature  ;

    /**
     * 电池仓锁状态
     */
    private boolean batteryCompartmentLockStatus;

    /**
     * 速度       单位 m/s
     */
    private float speed;

    /**
     * 定位
     */
    private Gps gps = new Gps();

    /**
     * 倾斜角度
     */
    private double inclinationAngle ;

    /**
     * 电流
     */
    private double current;

    /**
     * 电压
     */
    private double voltage;

    /**
     * 电量
     */
    private double capacity;

    /**
     * 扭力
     */
    private double torsion;

    /**
     * 电池温度
     */
    private double batteryTemperature;

    /**
     * 车外温度
     */
    private double externalTemperature;

    /**
     * 电机转速
     */
    private double motorSpeed;
    /**
     * 单次里程
     */
    private double singleMileage;
    /**
     * 爬坡角度
     */
    private double climbingAngle;


    public boolean isLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(boolean lockStatus) {
        this.lockStatus = lockStatus;
    }

    public boolean isTrunkLockStatus() {
        return trunkLockStatus;
    }

    public void setTrunkLockStatus(boolean trunkLockStatus) {
        this.trunkLockStatus = trunkLockStatus;
    }

    public float getTrunkTemperature() {
        return trunkTemperature;
    }

    public void setTrunkTemperature(float trunkTemperature) {
        this.trunkTemperature = trunkTemperature;
    }

    public boolean isBatteryCompartmentLockStatus() {
        return batteryCompartmentLockStatus;
    }

    public void setBatteryCompartmentLockStatus(boolean batteryCompartmentLockStatus) {
        this.batteryCompartmentLockStatus = batteryCompartmentLockStatus;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Gps getGps() {
        return gps;
    }

    public void setGps(Gps gps) {
        gps = gps;
    }

    public double getInclinationAngle() {
        return inclinationAngle;
    }

    public void setInclinationAngle(double inclinationAngle) {
        this.inclinationAngle = inclinationAngle;
    }

    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }

    public double getVoltage() {
        return voltage;
    }

    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getTorsion() {
        return torsion;
    }

    public void setTorsion(double torsion) {
        this.torsion = torsion;
    }

    public double getBatteryTemperature() {
        return batteryTemperature;
    }

    public void setBatteryTemperature(double batteryTemperature) {
        this.batteryTemperature = batteryTemperature;
    }

    public double getExternalTemperature() {
        return externalTemperature;
    }

    public void setExternalTemperature(double externalTemperature) {
        this.externalTemperature = externalTemperature;
    }

    public double getMotorSpeed() {
        return motorSpeed;
    }

    public void setMotorSpeed(double motorSpeed) {
        this.motorSpeed = motorSpeed;
    }

    public double getSingleMileage() {
        return singleMileage;
    }

    public void setSingleMileage(double singleMileage) {
        this.singleMileage = singleMileage;
    }

    public double getClimbingAngle() {
        return climbingAngle;
    }

    public void setClimbingAngle(double climbingAngle) {
        this.climbingAngle = climbingAngle;
    }

    @Override
    public String toString() {
        return "ReportMessage{" +
                "lockStatus=" + lockStatus +
                ", trunkLockStatus=" + trunkLockStatus +
                ", trunkTemperature=" + trunkTemperature +
                ", batteryCompartmentLockStatus=" + batteryCompartmentLockStatus +
                ", speed=" + speed +
                ", Gps=" + gps +
                ", inclinationAngle=" + inclinationAngle +
                ", current=" + current +
                ", voltage=" + voltage +
                ", capacity=" + capacity +
                ", torsion=" + torsion +
                ", batteryTemperature=" + batteryTemperature +
                ", externalTemperature=" + externalTemperature +
                ", motorSpeed=" + motorSpeed +
                ", singleMileage=" + singleMileage +
                ", climbingAngle=" + climbingAngle +
                '}';
    }
}
