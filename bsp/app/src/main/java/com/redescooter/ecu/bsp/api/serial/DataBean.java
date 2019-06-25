package com.redescooter.ecu.bsp.api.serial;

import com.redescooter.ecu.bsp.api.model.Gps;

import java.util.List;

public class DataBean {


    /**
     * 行为
     */
    private String action;
    /**
     * 数据
     */
    private data data;
    /**
     * code
     */
    private int code;

    public String getAction() {
        return action;
    }

    public void setAction(String Action) {
        this.action = Action;
    }

    public data getdata() {
        return data;
    }

    public void setdata(data data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "dataBean{" +
                "Action='" + action + '\'' +
                ", data=" + data +
                '}';
    }

    public static class data {


        /**
         * 速度   单位m/s
         */
        private float speed;
        /**
         * 电量百分比
         */
        private float battery;
        /**
         * 总里程  单位 米
         */
        private float totalMileage;


        /**
         * 单次里程  单位 米
         * 从开锁到关锁为一次里程
         */
        private float singleMileage;
        /**
         * 环境温度  单位 摄氏度
         */
        private float ambientTemperature;

        /**
         * 扭力       单位 牛顿
         */
        private float torque;
        /**
         * 爬坡角度   单位 度
         */
        private float climbingAngle;
        /**
         *
         * 时间
         */
        private String time;
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
         * 定位
         */
        private gps gps;

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
         * bms

         */
        private List<String> batteryIds;

        /**
         * 异步接口
         *      来自哪里
         */
        private String from;

        /**
         * 异步接口
         *      行为
         */
        private String event;

        /**
         * RFID id号
         */
        private String rfid;

        /**
         * 内容
         */
        private String key;


        public float getSpeed() {
            return speed;
        }

        public void setSpeed(float speed) {
            this.speed = speed;
        }

        public float getBattery() {
            return battery;
        }

        public void setBattery(float battery) {
            this.battery = battery;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public float getTotalMileage() {
            return totalMileage;
        }

        public void setTotalMileage(float totalMileage) {
            this.totalMileage = totalMileage;
        }

        public float getSingleMileage() {
            return singleMileage;
        }

        public void setSingleMileage(float singleMileage) {
            this.singleMileage = singleMileage;
        }

        public float getAmbientTemperature() {
            return ambientTemperature;
        }

        public void setAmbientTemperature(float ambientTemperature) {
            this.ambientTemperature = ambientTemperature;
        }

        public float getTorque() {
            return torque;
        }

        public void setTorque(float torque) {
            this.torque = torque;
        }

        public float getClimbingAngle() {
            return climbingAngle;
        }

        public void setClimbingAngle(float climbingAngle) {
            this.climbingAngle = climbingAngle;
        }

        public boolean getLockStatus() {
            return lockStatus;
        }

        public void setLockStatus(boolean lockStatus) {
            this.lockStatus = lockStatus;
        }

        public boolean getTrunkLockStatus() {
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

        public boolean getBatteryCompartmentLockStatus() {
            return batteryCompartmentLockStatus;
        }

        public void setBatteryCompartmentLockStatus(boolean batteryCompartmentLockStatus) {
            this.batteryCompartmentLockStatus = batteryCompartmentLockStatus;
        }

        public gps getGps() {
            return gps;
        }

        public void setGps(gps gps) {
            this.gps = gps;
        }

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

        public List<String> getBatteryIds() {
            return batteryIds;
        }

        public void setBatteryIds(List<String> batteryIds) {
            this.batteryIds = batteryIds;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getEvent() {
            return event;
        }

        public void setEvent(String event) {
            this.event = event;
        }

        public String getRfid() {
            return rfid;
        }

        public void setRfid(String rfid) {
            this.rfid = rfid;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        @Override
        public String toString() {
            return "data{" +
                    "speed=" + speed +
                    ", battery=" + battery +
                    ", totalMileage=" + totalMileage +
                    ", singleMileage=" + singleMileage +
                    ", ambientTemperature=" + ambientTemperature +
                    ", torque=" + torque +
                    ", climbingAngle=" + climbingAngle +
                    ", time='" + time + '\'' +
                    ", lockStatus=" + lockStatus +
                    ", trunkLockStatus=" + trunkLockStatus +
                    ", trunkTemperature=" + trunkTemperature +
                    ", batteryCompartmentLockStatus=" + batteryCompartmentLockStatus +
                    ", gps=" + gps +
                    ", inclinationAngle=" + inclinationAngle +
                    ", current=" + current +
                    ", voltage=" + voltage +
                    ", capacity=" + capacity +
                    ", torsion=" + torsion +
                    ", batteryTemperature=" + batteryTemperature +
                    ", externalTemperature=" + externalTemperature +
                    ", motorSpeed=" + motorSpeed +
                    ", batteryIds=" + batteryIds +
                    ", from='" + from + '\'' +
                    ", event='" + event + '\'' +
                    ", rfid='" + rfid + '\'' +
                    ", key='" + key + '\'' +
                    '}';
        }
    }

}
