package c.mars.ormliteex;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Constantine Mars on 6/19/15.
 */
@DatabaseTable
@Data @AllArgsConstructor @NoArgsConstructor
public class It {
    @DatabaseField
    private String s;
    @DatabaseField (id=true)
    private Integer i;
}
