package junit.io;

import java.util.*;

import junit.testsuite.*;

/**
 * CSVファイルを読み込み、行単位で対応するオブジェクトに変換してリストで返します。<br>
 * CSVファイルからテスト用の設定を生成する場合にこのクラスを継承して利用してください。<br>
 * なお、CSVファイルにはヘッダー行が含まれており、それを読み飛ばすという前提の仕様になっています。<br>
 * header-detailの2層構造のモデルを対象としています。<br>
 * ※CSVファイルがソートされている必要があります。
 * 
 * @author cobot_1
 * 
 * @param <T>
 *            生成するheaderオブジェクトのクラス
 * @param <E>
 *            生成するdetailオブジェクトのクラス
 */
public abstract class DualKeyConfigLoader<T, E> {

    /**
     * 指定されたCSVファイルを読み込み、オブジェクトに変換してリスト形式で返します。
     * 
     * @return CSVファイルから生成されたオブジェクトのリスト
     */
    public List<T> load() {
        final CSVLoader loader = new CSVLoader();
        final List<String[]> records = loader.load(TestProperties.getInstance().getCsvRootDir() + getCsvFileName(),
                getColumnCount());

        List<T> result = createList();

        T subList = null;
        final int recordCount = records.size();
        for (int i = 0; i < recordCount; i++) {
            E entity = createEntity(records.get(i));

            if (needNewSublist(subList, records.get(i))) {
                subList = createSubList(records.get(i));
                result.add(subList);
            }

            addToSubList(subList, entity);
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
     * CSVから生成されたオブジェクトを格納するリストを生成します。
     * 
     * @return 生成したheaderオブジェクトを格納するリスト
     */
    protected abstract List<T> createList();

    /**
     * 生成したdetailオブジェクトを格納するheaderオブジェクトを生成します。
     * 
     * @param values
     * @return 生成したdetailオブジェクトを格納するheaderオブジェクト
     */
    protected abstract T createSubList(String[] values);

    /**
     * CSVの各行の値から対応するオブジェクトを生成して返します。
     * 
     * @param values
     *            CSVの各行の文字列配列
     * @return CSVの各行に対応するオブジェクト
     */
    protected abstract E createEntity(String[] values);

    /**
     * headerオブジェクトと読み込まれた行の値を比較し、<br>
     * headerオブジェクトを切り替える必要があるかを返します。
     * 
     * @param subList
     *            現在のheaderオブジェクト
     * @param values
     *            CSVの各行の文字列配列
     * @return true: 新しいheaderオブジェクトを生成する必要がある
     */
    protected abstract boolean needNewSublist(T subList, String[] values);

    /**
     * headerオブジェクトにdetailオブジェクトを追加します。
     * 
     * @param subList
     *            headerオブジェクト
     * @param entity
     *            detailオブジェクト
     */
    protected abstract void addToSubList(T subList, E entity);
}
