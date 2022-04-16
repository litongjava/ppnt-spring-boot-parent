package top.ppnt.mybatis.plus.extension;

import com.baomidou.mybatisplus.core.conditions.AbstractLambdaWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;

/**
 * @author Ping E Lee
 *
 */
@SuppressWarnings("serial")
public class ColumnResolver<T> extends AbstractLambdaWrapper<T, ColumnResolver<T>> {

  @Override
  protected ColumnResolver<T> instance() {
    return null;
  }

  @Override
  public String columnsToString(SFunction<T, ?>... columns) {
    return super.columnsToString(columns);
  }

  @Override
  public String columnsToString(boolean onlyColumn, SFunction<T, ?>... columns) {
    return super.columnsToString(onlyColumn, columns);
  }

  @Override
  public String columnToString(SFunction<T, ?> column) {
    return super.columnToString(column);
  }

  @Override
  public String columnToString(SFunction<T, ?> column, boolean onlyColumn) {
    return super.columnToString(column, onlyColumn);
  }
}