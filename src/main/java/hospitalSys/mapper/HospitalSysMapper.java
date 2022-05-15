package hospitalSys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hospitalSys.bean.ClinicFindingsBean;
import hospitalSys.bean.DocumentOrderBean;
import hospitalSys.bean.GeneralPurposeBean;
import hospitalSys.bean.HomePageBean;
import hospitalSys.bean.IllnessIdAndDocumentIDBean;
import hospitalSys.bean.IntroductoryDocumentInfoBean;
import hospitalSys.bean.KanjaInfoBean;
import hospitalSys.bean.OrderInfoBean;
import hospitalSys.bean.TBean;

@Mapper
public interface HospitalSysMapper {

	// 获取每个用户最新的病例信息
	List<HomePageBean> selectHomePageBean();

	// 根据id查询用户信息（病例用）
	List<DocumentOrderBean> selectById(int kanjaId);

	// 根据id查询id信息（主页用）
	List<HomePageBean> selectId(int kanjaId);

	// 保存新用户信息
	KanjaInfoBean selectKanjaInfoByKanjaId(int kanjaId);

	// 保存新介绍文书信息
	void saveNewKanjaInfo(IntroductoryDocumentInfoBean introductoryDocumentInfo);

	// 更改病例状态
	void statusConfirmation(int documentId);

	// 删除病例
	void deleteDocument(int documentId);

	// 查询当前d表id最大值，然后新id等于它加1
	IllnessIdAndDocumentIDBean selectMaxDId();

	// 查询i表最大id
	IllnessIdAndDocumentIDBean selectMaxIId();

	// 保存介绍状
	void saveIntroductionInfo(IntroductoryDocumentInfoBean introductoryDocumentInfo);

	// 保存定型诊疗所见
	void saveNewClinicFindings(ClinicFindingsBean clinicFindingsBean);

	// 保存定型汎用紹介状
	void saveNewGeneralPurpose(GeneralPurposeBean gseneralPurposeBean);

	// 获取所有信息，之后根据不同不同文书类型 去显示不同内容
	OrderInfoBean selectInfoById(int kanjaId);

}
