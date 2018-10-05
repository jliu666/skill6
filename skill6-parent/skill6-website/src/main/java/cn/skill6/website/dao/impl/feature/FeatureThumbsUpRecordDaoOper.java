package cn.skill6.website.dao.impl.feature;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.skill6.common.entity.po.feature.FeatureThumbsUpRecord;
import cn.skill6.common.exception.general.NullPointerException;
import cn.skill6.website.dao.intf.feature.FeatureThumbsUpRecordDao;
import cn.skill6.website.dao.mappers.feature.FeatureThumbsUpRecordMapper;

/**
 * 点赞记录操作实现类
 *
 * @author 何明胜
 * @version 1.2
 * @since 2018年8月28日 上午12:19:05
 */
public class FeatureThumbsUpRecordDaoOper implements FeatureThumbsUpRecordDao {
  private static final Logger logger = LoggerFactory.getLogger(FeatureThumbsUpRecordDaoOper.class);

  @Autowired private FeatureThumbsUpRecordMapper featureThumbsUpRecordMapper;

  @Override
  public int deleteByPrimaryKey(Long userId, Long articleId, String thumbsUpType) {
    logger.warn("删除用户id为{},文章id为{},类型为{}的点赞记录", userId, articleId);

    return featureThumbsUpRecordMapper.deleteByPrimaryKey(userId, articleId, thumbsUpType);
  }

  @Override
  public void addThumbsUpRecord(FeatureThumbsUpRecord featureThumbsUpRecord) {
    if (featureThumbsUpRecord == null
        || featureThumbsUpRecord.getUserId() == null
        || featureThumbsUpRecord.getArticleId() == null
        || featureThumbsUpRecord.getThumbsUpType() == null) {
      throw new NullPointerException("点赞记录或者点赞用户id或者文章id或者点赞类型不能为空");
    }
    featureThumbsUpRecord.setThumbsUpTime(new Date());

    featureThumbsUpRecordMapper.insert(featureThumbsUpRecord);
    logger.info("增加点赞记录成功,{}", featureThumbsUpRecord);
  }

  @Override
  public FeatureThumbsUpRecord findByPrimaryKey(Long userId, Long articleId, String thumbsUpType) {
    if (userId == null || articleId == null || thumbsUpType == null) {
      throw new NullPointerException("点赞用户id或者文章id或者点赞类型不能为空");
    }

    FeatureThumbsUpRecord featureThumbsUpRecord =
        featureThumbsUpRecordMapper.selectByPrimaryKey(userId, articleId, thumbsUpType);
    logger.info("根据主键找到点赞记录,{}", featureThumbsUpRecord);

    return featureThumbsUpRecord;
  }

  @Override
  public List<FeatureThumbsUpRecord> findAll() {
    List<FeatureThumbsUpRecord> featureThumbsUpRecords = featureThumbsUpRecordMapper.selectAll();
    logger.info("找到所有点赞记录,{}", featureThumbsUpRecords);

    return featureThumbsUpRecords;
  }

  @Override
  public void modifyByPrimaryKey(FeatureThumbsUpRecord featureThumbsUpRecord) {
    if (featureThumbsUpRecord == null
        || featureThumbsUpRecord.getUserId() == null
        || featureThumbsUpRecord.getArticleId() == null
        || featureThumbsUpRecord.getThumbsUpType() == null) {
      throw new NullPointerException("点赞记录或者点赞用户id或者文章id或者点赞类型不能为空");
    }

    logger.info("成功修改点赞记为{}", featureThumbsUpRecord);
  }
}