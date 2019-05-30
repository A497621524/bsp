package android.databinding;

public class DataBinderMapperImpl extends MergedDataBinderMapper {
  DataBinderMapperImpl() {
    addMapper(new com.redescooter.ecu.bsp.api.DataBinderMapperImpl());
  }
}
