package com.service;

import com.dao.NoteContentMapper;
import com.entity.NoteContent;
import com.util.TransformFile;
import com.util.SessionUtil;
import com.vo.NoteParamVo;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CAIYUHUI
 * @create 2018/09/21 18:19
 **/
@Service("com.service.NoteContentService")
public class NoteContentService {

    @Resource(name = "com.dao.NoteContentMapper")
    private NoteContentMapper noteContentMapper;

    /**
     * 新增笔记(插入笔记的内容)
     *
     * @param note 笔记内容实体类（笔记内容，目录id）
     * @return 返回数据插入成功与否
     * @throws Exception
     */
    public boolean addNoteContent(NoteContent note, HttpServletRequest request) throws Exception {
        note.setCreator(SessionUtil.getCurrentUser(request));
        return noteContentMapper.insert(note) > 0;
    }

    /**
     * 查询笔记内容和名字
     *
     * @param id 笔记id值
     * @return
     */
    public NoteParamVo queryNoteInfo(String id) {
        List<NoteParamVo> res = noteContentMapper.queryNoteInfo(id);
        if (res.size() > 0) {
            return res.get(0);
        } else {
            return new NoteParamVo();
        }
    }

    /**
     * 修改笔记内容
     *
     * @param noteParamVo content id
     * @return true or false
     */
    public boolean updateNote(NoteParamVo noteParamVo, HttpServletRequest request) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("content", noteParamVo.getContent());
        map.put("id", noteParamVo.getId());
        map.put("shareUrl", noteParamVo.getShareUrl());
        map.put("modifier", SessionUtil.getCurrentUser(request));
        return noteContentMapper.updateNote(map) > 0;
    }

    /**
     * 导出笔记到pdf文件中
     *
     * @param noteId 笔记id
     */
    public ResponseEntity<byte[]> exportNote(String noteId) {
        NoteParamVo note = queryNoteInfo(noteId);
        String name = note.getLabel();
        if (name != null && note.getContent() != null) {
            TransformFile util = new TransformFile(note.getContent(), name);
            util.produce();//生成pdf文件

            File file = new File(util.pdfPath());
            HttpHeaders headers = new HttpHeaders();//创建响应头对象,设置响应信息
            try {
                String fileName = new String(name.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
                //设置响应内容处理方式为附件,并指定文件名
                headers.setContentDispositionFormData("attachment", fileName);
                //设置响应头类型为 application/octet-stream 流类型
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                //将文件转换成字节数组
                byte[] bytes = FileUtils.readFileToByteArray(file);
                //创建ResponseEntity对象(封装文件字节数组,响应头,响应状态码)
                return new ResponseEntity<>(bytes, headers, HttpStatus.CREATED);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

}
