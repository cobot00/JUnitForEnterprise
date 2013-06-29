package junit.io;

import java.util.*;

import junit.io.*;
import junit.testsuite.*;

/**
 * CSVファイルを読み込み、行単位で対応するオブジェクトに変換してリストで返します。<br>
 * CSVファイルからテスト用の設定を生成する場合にこのクラスを継承して利用してください。<br>
 * なお、CSVファイルにはヘッダー行が含まれており、それを読み飛ばすという前提の仕様になっています。<br>
 * レコードキーが2つ存在するモデルの場合はAbstractConfigWithSubListBuilderを利用してください。
 * 
 * @author cobot00
 * 
 * @param <E>
 *            生成するオブジェクトのクラス
 */
public abstract class ConfigLoader<E> {

    /**
     * 指定されたCSVファイルを読み込み、オブジェクトに変換してリスト形式で返します。
     * 
     * @return CSVファイルから生成されたオブジェクトのリスト
     */
    public List<E> load() {
        final CSVLoader loader = new CSVLoader();
        final List<String[]> records = loader.load(TestProperties.getInstance().getCsvRootDir() + getCsvFileName(),
                getColumnCount());

        List<E> result = createList();

        final int recordCount = records.size();
        for (int i = 0; i < recordCount; i++) {
            result.add(createEntity(records.get(i)));
        }

        return result;
    }

    /**
     * CSVファイルに含まれる列数を返します。
     * 
     * @return CSVファイルに含まれる列数
     */
    protected abstract int getColumnCount();

    /**
     * CSVフォルダのルートディレクトリからの相対パスを返します。<br>
     * <br>
     * 例 c\:projectA\csv\hoge.csvの場合は「hoge.csv」を返す(ルートディレクトリは「c\:projectA\csv」)。<br>
     * 
     * @return 読み込み対象のCSVファイルのCSVルートディレクトリからの相対パス
     */
    protected abstract String getCsvFileName();

    /**
     * CSVから生成されたオブジェクトを格納するリストを生成して返します。
     * 
     * @return 生成したオブジェクトを格納するリスト
     */
    protected abstract List<E> createList();

    /**
     * CSVの各行の値から対応するオブジェクトを生成して返します。
     * 
     * @param values
     * @return CSVの各行に対応するオブジェクト
     */
    protected abstract E createEntity(String[] values);
}
