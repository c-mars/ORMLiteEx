package c.mars.ormliteex;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

import lombok.Data;
import timber.log.Timber;

/**
 * Created by Constantine Mars on 6/19/15.
 */
@Data
public class Db {

    private Helper helper;
    private static Db instance;
    public static void init(Context context){
        if(instance==null){
            instance=new Db(context);
        }
    }
    public static Db getInstance(){
        return instance;
    }
    private Db(Context context){
        helper=new Helper(context);
    }

    public List<It> getAll(){
        List<It> its = null;
        try {
            its=helper.getDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            Timber.e(e.getMessage());
        }
        return its;
    }

    public void add(It it){
        try {
            helper.getDao().create(it);
        } catch (SQLException e) {
            e.printStackTrace();
            Timber.e(e.getMessage());
        }
    }

    public void removeAll(){
        try {
            List<It> its=helper.getDao().queryForAll();
            for(It it:its) {
                helper.getDao().delete(it);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Timber.e(e.getMessage());
        }
    }

    private class Helper extends OrmLiteSqliteOpenHelper {
        private Dao<It, String> dao;

        public Helper(Context context) {
            super(context, "it.sqlite", null, 1);

        }

        @Override
        public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
            try {
                TableUtils.createTable(connectionSource, It.class);
            } catch (SQLException e) {
                e.printStackTrace();
                Timber.e(e.getMessage());
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        }

        public Dao<It, String> getDao() {
            if (dao == null) {
                try {
                    dao = getDao(It.class);
                } catch (SQLException e) {
                    e.printStackTrace();
                    Timber.e(e.getMessage());
                }
            }
            return dao;
        }
    }
}