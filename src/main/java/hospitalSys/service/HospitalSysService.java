package hospitalSys.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import hospitalSys.bean.ClinicFindingsBean;
import hospitalSys.bean.DocumentOrderBean;
import hospitalSys.bean.GeneralPurposeBean;
import hospitalSys.bean.HomePageBean;
import hospitalSys.bean.IllnessIdAndDocumentIDBean;
import hospitalSys.bean.IntroductoryDocumentInfoBean;
import hospitalSys.bean.KanjaInfoBean;
import hospitalSys.bean.OrderInfoBean;
import hospitalSys.bean.TBean;
import hospitalSys.mapper.HospitalSysMapper;
import lombok.Getter;
import lombok.Setter;

@Service
public class HospitalSysService {

	@Autowired
	private HospitalSysMapper hospitalSysMapper;

	// メイン画面のデータ
	public List<HomePageBean> selectHomePage() {

		List<HomePageBean> list = hospitalSysMapper.selectHomePageBean();
		for (HomePageBean x : list) {
			if (x.getFlag().equals("1")) {
				x.setFlag("未確認");
			} else {
				x.setFlag("確認済み");
			}

		}

		return list;
	}

	public List<DocumentOrderBean> selectByKanjaId(int kanjaId) {
		List<DocumentOrderBean> list = hospitalSysMapper.selectById(kanjaId);

		return list;
	}

	public List<HomePageBean> selectIdSe(int kanjaId) {

		List<HomePageBean> list = hospitalSysMapper.selectId(kanjaId);

		for (HomePageBean x : list) {
			if (x.getFlag().equals("1")) {
				x.setFlag("未確認");
			} else {
				x.setFlag("確認済み");
			}

		}

		return list;
	}

	public KanjaInfoBean selectKanjaInfoByKanjaId(int kanjaId) {

		return hospitalSysMapper.selectKanjaInfoByKanjaId(kanjaId);
	}

	public void saveNewKanja(IntroductoryDocumentInfoBean introductoryDocumentInfo) {

		Timestamp time = new Timestamp(System.currentTimeMillis());

		introductoryDocumentInfo.setCreateTime(time);

		hospitalSysMapper.saveNewKanjaInfo(introductoryDocumentInfo);

	}

	// 更改状态的方法
	public void statusConfirmationOfHomePage(int documentId) {

		hospitalSysMapper.statusConfirmation(documentId);

	}

	// 删除文书的方法
	public void deleteDocumentIdById(int documentId) {

		hospitalSysMapper.deleteDocument(documentId);
	}

	// 获取d表最大值的方法 用来更新id
	public int selectMaxIdbyD() {
		IllnessIdAndDocumentIDBean idb = hospitalSysMapper.selectMaxDId();

		return idb.getDocumentId();
	}

	public int selectMaxIdByI() {

		IllnessIdAndDocumentIDBean idb = hospitalSysMapper.selectMaxIId();

		return idb.getIllnessId();
	}

	// 保存紹介状
	public void saveIntroduction(IntroductoryDocumentInfoBean idi) {
		Timestamp time = new Timestamp(System.currentTimeMillis());
		idi.setCreateTime(time);

		hospitalSysMapper.saveIntroductionInfo(idi);

	}

	// 診療所見
	public void saveClinicFindingsBean(ClinicFindingsBean cfb) {

		Timestamp time = new Timestamp(System.currentTimeMillis());
		cfb.setCreateTime(time);
		hospitalSysMapper.saveNewClinicFindings(cfb);

	}

	// 汎用紹介状
	public void saveGeneralPurposeBean(GeneralPurposeBean gpb) {
		Timestamp time = new Timestamp(System.currentTimeMillis());
		gpb.setCreateTime(time);
		hospitalSysMapper.saveNewGeneralPurpose(gpb);

	}

	public OrderInfoBean selectInfoByKanjaId(int kanjaId) {

		return hospitalSysMapper.selectInfoById(kanjaId);

	}
}
