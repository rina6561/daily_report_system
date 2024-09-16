package models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 日報データのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_REP) //JpaConstの日報テーブルを指定
@NamedQueries({

    //全ての従業員をidの降順に取得する
    @NamedQuery(
            name = JpaConst.Q_REP_GET_ALL,
            query = JpaConst.Q_REP_GET_ALL_DEF),
    //ENTITY_EMP = "employee"; //従業員
    //Q_EMP_GET_ALL = ENTITY_EMP + ".getAll";
    //Q_REP_GET_ALL_DEF = "SELECT r FROM Report AS r ORDER BY r.id DESC"


    //全ての日報の件数を取得する
    @NamedQuery(
            name = JpaConst.Q_REP_COUNT,
            query = JpaConst.Q_REP_COUNT_DEF),
    //ENTITY_REP = "report"; //日報
    //Q_REP_COUNT = ENTITY_REP + ".count";
    // Q_REP_COUNT_DEF = "SELECT COUNT(r) FROM Report AS r"


  //指定した従業員が作成した日報を全件idの降順で取得する
    @NamedQuery(
            name = JpaConst.Q_REP_GET_ALL_MINE,
            query = JpaConst.Q_REP_GET_ALL_MINE_DEF),
    //Q_REP_GET_ALL_MINE = ENTITY_REP + ".getAllMine";
    //Q_REP_GET_ALL_MINE_DEF = "SELECT r FROM Report AS r WHERE r.employee = :" + JPQL_PARM_EMPLOYEE + " ORDER BY r.id DESC";


  //指定した従業員が作成した日報の件数を取得する
    @NamedQuery(
            name = JpaConst.Q_REP_COUNT_ALL_MINE,
            query = JpaConst.Q_REP_COUNT_ALL_MINE_DEF)
    //Q_REP_COUNT_ALL_MINE = ENTITY_REP + ".countAllMine";
    // Q_REP_COUNT_ALL_MINE_DEF = "SELECT COUNT(r) FROM Report AS r WHERE r.employee = :" + JPQL_PARM_EMPLOYEE;

})

@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)

@Entity
public class Report{

    /**
     * id
     */
    @Id //主キー
    @Column(name = JpaConst.REP_COL_ID) //データベースの列名を定義
    @GeneratedValue(strategy = GenerationType.IDENTITY) //主キーがどのように生成されるかを指定
    private Integer id; //主キーを格納するためのフィールド

    /**
     * 日報を登録した従業員
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.REP_COL_EMP, nullable = false) // @JoinColumn：外部キーで、employee_idという列名を指定
    private Employee employee;

    /**
     * いつの日報かを示す日付
     */
    @Column(name = JpaConst.REP_COL_REP_DATE, nullable = false)
    private LocalDate reportDate;

    /**
     * 日報のタイトル
     */
    @Column(name = JpaConst.REP_COL_TITLE, length = 255, nullable = false)
    private String title;

    /**
     * 日報の内容
     */
    @Lob //テキストエリアの指定を行うアノテーション（改行もデータベースに保存できる）
    @Column(name = JpaConst.REP_COL_CONTENT, nullable = false)
    private String content;

    /**
     * 登録日時
     */
    @Column(name = JpaConst.REP_COL_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    @Column(name = JpaConst.REP_COL_UPDATED_AT, nullable = false)
    private LocalDateTime updatedAt;


}