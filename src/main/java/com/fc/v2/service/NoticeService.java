package com.fc.v2.service;

import com.fc.v2.common.base.BaseService;
import com.fc.v2.common.support.ConvertUtil;
import com.fc.v2.mapper.NoticeMapper;
import com.fc.v2.mapper.RelationNoticeUserMapper;
import com.fc.v2.model.auto.Notice;
import com.fc.v2.model.auto.RelationNoticeUser;
import com.fc.v2.model.auto.SysNoticeExample;
import com.fc.v2.model.auto.SysNoticeUserExample;
import com.fc.v2.model.auto.SysNoticeUserExample.Criteria;
import com.fc.v2.model.auto.TsysUserExample;
import com.fc.v2.model.auto.User;
import com.fc.v2.model.custom.Tablepar;
import com.fc.v2.satoken.SaTokenUtil;
import com.fc.v2.util.SnowflakeIdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 公告 SysNoticeService
 **/
@Service
public class SysNoticeService implements BaseService<Notice, SysNoticeExample> {

  @Autowired
  private NoticeMapper noticeMapper;
  @Autowired
  private SysUserService sysUserService;
  @Autowired
  private SysNoticeUserService sysNoticeUserService;
  @Autowired
  private RelationNoticeUserMapper relationNoticeUserMapper;

  /**
   * 分页查询
   *
   * @param pageNum
   * @param pageSize
   * @return
   */
  public PageInfo<Notice> list(Tablepar tablepar, String name) {
    SysNoticeExample testExample = new SysNoticeExample();
    testExample.setOrderByClause("id ASC");
    if (name != null && !"".equals(name)) {
      testExample.createCriteria().andTitleLike("%" + name + "%");
    }

    PageHelper.startPage(tablepar.getPage(), tablepar.getLimit());
    List<Notice> list = noticeMapper.selectByExample(testExample);
    PageInfo<Notice> pageInfo = new PageInfo<Notice>(list);
    return pageInfo;
  }


  /**
   * 对应用户的所有公告信息
   *
   * @param pageNum
   * @param pageSize
   * @return
   */
  public PageInfo<Notice> list(User user, Tablepar tablepar, String name) {
    //查询未阅读的公告用户外键
    SysNoticeUserExample sysNoticeUserExample = new SysNoticeUserExample();
    Criteria criteria = sysNoticeUserExample.createCriteria();
    criteria.andUserIdEqualTo(user.getId());
    List<RelationNoticeUser> noticeUsers = relationNoticeUserMapper.selectByExample(
        sysNoticeUserExample);
    if (noticeUsers != null && noticeUsers.size() > 0) {
      //查询对应的公告列表
      List<String> ids = new ArrayList<String>();
      for (RelationNoticeUser relationNoticeUser : noticeUsers) {
        ids.add(relationNoticeUser.getNoticeId());
      }

      //分页查询对应用户的所有公告信息
      SysNoticeExample testExample = new SysNoticeExample();
      testExample.setOrderByClause("id desc");
      com.fc.v2.model.auto.SysNoticeExample.Criteria criteria1 = testExample.createCriteria();
      if (name != null && !"".equals(name)) {
        criteria1.andTitleLike("%" + name + "%");
      }

      criteria1.andIdIn(ids);
      PageHelper.startPage(tablepar.getPage(), tablepar.getLimit());
      List<Notice> list = noticeMapper.selectByExample(testExample);

      PageInfo<Notice> pageInfo = new PageInfo<Notice>(list);

      return pageInfo;
    }

    return new PageInfo<Notice>();


  }


  @Override
  public int deleteByPrimaryKey(String ids) {
    List<String> lista = ConvertUtil.toListStrArray(ids);
    SysNoticeExample example = new SysNoticeExample();
    example.createCriteria().andIdIn(lista);
    return noticeMapper.deleteByExample(example);
  }


  @Override
  public Notice selectByPrimaryKey(String id) {

    return noticeMapper.selectByPrimaryKey(id);
  }


  @Override
  public int updateByPrimaryKeySelective(Notice record) {
    return noticeMapper.updateByPrimaryKeySelective(record);
  }

  /**
   * 添加
   */
  @Override
  @Transactional
  public int insertSelective(Notice record) {
    //添加雪花主键id
    record.setId(SnowflakeIdWorker.getUUID());
    //添加创建人id
    record.setCreateId(SaTokenUtil.getUserId());
    //添加创建人
    record.setCreateUsername(SaTokenUtil.getLoginName());
    //添加创建时间
    record.setCreateTime(new Date());
    noticeMapper.insertSelective(record);
    //给所有人添加公告状态
    List<User> list = sysUserService.selectByExample(new TsysUserExample());
    for (User user : list) {
      RelationNoticeUser noticeUser = new RelationNoticeUser(null, record.getId(), user.getId(), 0);
      sysNoticeUserService.insertSelective(noticeUser);
    }
    return 1;
  }


  @Override
  public int updateByExampleSelective(Notice record, SysNoticeExample example) {

    return noticeMapper.updateByExampleSelective(record, example);
  }


  @Override
  public int updateByExample(Notice record, SysNoticeExample example) {

    return noticeMapper.updateByExample(record, example);
  }

  @Override
  public List<Notice> selectByExample(SysNoticeExample example) {

    return noticeMapper.selectByExample(example);
  }


  @Override
  public long countByExample(SysNoticeExample example) {

    return noticeMapper.countByExample(example);
  }


  @Override
  public int deleteByExample(SysNoticeExample example) {

    return noticeMapper.deleteByExample(example);
  }

  /**
   * 检查name
   *
   * @param notice
   * @return
   */
  public int checkNameUnique(Notice notice) {
    SysNoticeExample example = new SysNoticeExample();
    example.createCriteria().andTitleEqualTo(notice.getTitle());
    List<Notice> list = noticeMapper.selectByExample(example);
    return list.size();
  }

  /**
   * 获取用户未阅读公告
   *
   * @param user
   * @param state 阅读状态  0未阅读 1 阅读  -1全部
   * @return
   * @author fuce
   * @Date 2019年9月8日 上午3:36:21
   */
  public List<Notice> getuserNoticeNotRead(User user, int state) {
    List<Notice> notices = new ArrayList<>();
    //查询未阅读的公告用户外键
    SysNoticeUserExample sysNoticeUserExample = new SysNoticeUserExample();
    Criteria criteria = sysNoticeUserExample.createCriteria();
    criteria.andUserIdEqualTo(user.getId());
    if (-1 != state) {
      criteria.andStateEqualTo(state);
    }
    List<RelationNoticeUser> noticeUsers = relationNoticeUserMapper.selectByExample(
        sysNoticeUserExample);
    if (noticeUsers != null && noticeUsers.size() > 0) {
      //查询对应的公告列表
      List<String> ids = new ArrayList<String>();
      for (RelationNoticeUser relationNoticeUser : noticeUsers) {
        ids.add(relationNoticeUser.getNoticeId());
      }
      SysNoticeExample noticeExample = new SysNoticeExample();
      noticeExample.createCriteria().andIdIn(ids);
      notices = noticeMapper.selectByExample(noticeExample);
    }
    return notices;
  }


  /**
   * 根据公告id把当前用户的公告置为以查看
   *
   * @param noticeid
   * @author fuce
   * @Date 2019年9月8日 下午7:14:19
   */
  public void editUserState(String noticeid) {
    //SysNoticeUser
    SysNoticeUserExample sysNoticeUserExample = new SysNoticeUserExample();
    sysNoticeUserExample.createCriteria().andNoticeIdEqualTo(noticeid)
        .andUserIdEqualTo(SaTokenUtil.getUserId());
    List<RelationNoticeUser> noticeUsers = relationNoticeUserMapper.selectByExample(
        sysNoticeUserExample);
    for (RelationNoticeUser relationNoticeUser : noticeUsers) {
      relationNoticeUser.setState(1);
      relationNoticeUserMapper.updateByPrimaryKey(relationNoticeUser);
    }
  }

  /**
   * 获取最新8条公告
   *
   * @return
   */
  public List<Notice> getNEW() {
    SysNoticeExample testExample = new SysNoticeExample();
    testExample.setOrderByClause("id DESC");
    PageHelper.startPage(1, 8);
    List<Notice> list = noticeMapper.selectByExample(testExample);
    return list;
  }


}
