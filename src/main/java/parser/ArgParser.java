package parser;

import dao.DiaryDao;
import vo.DiaryVO;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class ArgParser {

    private final String add = "add";
    private final String remove = "rm";
    private final String findAll = "all";
    private final String findOne = "open";
    private final String help = "help";

    private final DiaryDao diaryDao;

    public ArgParser(DiaryDao diaryDao) {
        this.diaryDao = diaryDao;
    }

    public void parse(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String command = args[0];

        switch (command) {
            case help:
                System.out.println("help");
                break;
            case add:
                System.out.println();
                System.out.print("제목 : ");
                String title = scanner.nextLine();
                System.out.println();

                System.out.print("내용 : ");
                String content = scanner.nextLine();
                DiaryVO diaryVO = new DiaryVO(title, content);
                diaryDao.save(diaryVO);
                System.out.println("일기 저장 완료");
                break;
            case remove:
                int deleteId = Integer.parseInt(args[1]);
                diaryDao.deleteOne(deleteId);
                System.out.println("일기 삭제 완료");
                break;
            case findAll:
                System.out.println("ID \t 제목 \t 날짜");
                for (DiaryVO vo : diaryDao.findAll()) {
                    System.out.println(vo.getId() + "\t" + vo.getTitle() + "\t" + vo.getCreatedAt());
                }
                break;
            case findOne:
                int findOne = Integer.parseInt(args[1]);
                DiaryVO vo;
                try {
                    vo = diaryDao.findOne(findOne);
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                    return;
                }

                System.out.println("제목");
                System.out.println(vo.getTitle());

                System.out.println("내용");
                System.out.println(vo.getContent());

                System.out.println("날짜");
                System.out.println(vo.getCreatedAt());
                break;
            default:
                System.out.println("올바르지 못한 명령어");
                break;
        }

    }
}
