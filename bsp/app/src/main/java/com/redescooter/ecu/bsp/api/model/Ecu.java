package com.redescooter.ecu.bsp.api.model;

public class Ecu {

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

    /**
     * 档位
     */
    private int gears;

    public static class gps {


        /**
         * GPGSV : 3,1,10,20,78,331,45,01,59,235,47,22,41,069,,13,32,252,45*70
         * GPGLL : 4250.5589,S,14718.5084,E,092204.999,A*2D
         * GPRMC : 024813.640,A,3158.4608,N,11848.3737,E,10.05,324.27,150706,,,A*50
         * GPGGA : 092204.999,4250.5589,S,14718.5084,E,1,04,24.4,12.2,M,19.7,M,0000*1F
         * GPGSA :  A,3,01,20,19,13,,,,,,,,,40.4,24.4,32.2*0A
         */

        private String GPGSV;
        private String GPGLL;
        private String GPRMC;
        private String GPGGA;
        private String GPGSA;

        public String getGPGSV() {
            return GPGSV;
        }

        public void setGPGSV(String GPGSV) {
            this.GPGSV = GPGSV;
        }

        public String getGPGLL() {
            return GPGLL;
        }

        public void setGPGLL(String GPGLL) {
            this.GPGLL = GPGLL;
        }

        public String getGPRMC() {
            return GPRMC;
        }

        public void setGPRMC(String GPRMC) {
            this.GPRMC = GPRMC;
        }

        public String getGPGGA() {
            return GPGGA;
        }

        public void setGPGGA(String GPGGA) {
            this.GPGGA = GPGGA;
        }

        public String getGPGSA() {
            return GPGSA;
        }

        public void setGPGSA(String GPGSA) {
            this.GPGSA = GPGSA;
        }

        @Override
        public String toString() {
            return "gps{" +
                    "GPGSV='" + GPGSV + '\'' +
                    ", GPGLL='" + GPGLL + '\'' +
                    ", GPRMC='" + GPRMC + '\'' +
                    ", GPGGA='" + GPGGA + '\'' +
                    ", GPGSA='" + GPGSA + '\'' +
                    '}';
        }

    }

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
        this.gps = gps;
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

    public int getGears() {
        return gears;
    }

    public void setGears(int gears) {
        this.gears = gears;
    }

    @Override
    public String toString() {
        return "Mcu{" +
                "lockStatus=" + lockStatus +
                ", trunkLockStatus=" + trunkLockStatus +
                ", trunkTemperature=" + trunkTemperature +
                ", batteryCompartmentLockStatus=" + batteryCompartmentLockStatus +
                ", speed=" + speed +
                ", gps=" + gps.toString() +
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
                ", gears=" + gears +
                '}';
    }


}
