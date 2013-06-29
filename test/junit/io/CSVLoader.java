package junit.io;

import java.io.*;
import java.util.*;

/**
 * テスト用の設定をCSVファイルからロードする場合に使用してください。<br>
 * CSVファイルの列数、CSVファイルのフルパスを指定する必要があります。
 * 
 * @author cobot_1
 */
public class CSVLoader {

    /**
     * CSVファイルを読み込み、行単位で文字列配列に変換してリストとして返します。<br>
     * CSVファイルの1行目はヘッダー行のため結果リストには含まれません。
     * 
     * @param filePath
     *            CSVファイルのフルパス
     * @param aColumnCount
     *            CSVファイルに含まれる列数
     * @return 文字列配列のリストを返します
     */
    public List<String[]> load(String filePath, int columnCount) {
        return load(filePath, columnCount, true);
    }

    /**
     * CSVファイルを読み込み、行単位で文字列配列に変換してリストとして返します。
     * 
     * @param filePath
     *            CSVファイルのフルパス
     * @param columnCount
     *            CSVファイルに含まれる列数
     * @param includeHeader
     *            CSVファイルにヘッダー行が含まれるか(true: ヘッダー行を含む)
     * @return 文字列配列のリストを返します
     */
    public List<String[]> load(String filePath, int columnCount, boolean includeHeader) {
        System.out.println("File Path = " + filePath);

        final List<String[]> result = new ArrayList<String[]>();

        try {
            final File csv = new File(filePath);
            final BufferedReader br = new BufferedReader(new FileReader(csv));

            String line = "";
            boolean isFirstRow = includeHeader;
            while ((line = br.readLine()) != null) {
                // ヘッダー行は読み飛ばす
                if (isFirstRow) {
                    isFirstRow = false;
                    continue;
                }

                // レコードを列単位に分割する
                final String[] tokens = line.split(",");

                // ロードした行の格納レコードの生成
                final String[] resultRow = new String[columnCount];

                for (int i = 0; i < columnCount; i++) {
                    resultRow[i] = tokens[i].replace("\"", "");
                }
                result.add(resultRow);
            }
            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        System.out.println("Result count = " + result.size());

        return result;
    }
}
