package edu.wzm.dao.replica;

import edu.wzm.entity.Product;
import org.apache.ibatis.annotations.Param;

/**
 * @author: wangzhiming
 * @Date: 2020/10/27
 * @version:
 * @Description:
 */
public interface ProductDao {

    Product findById(@Param("id") int id);
}
