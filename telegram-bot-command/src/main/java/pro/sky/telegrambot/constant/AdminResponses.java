package pro.sky.telegrambot.constant;

/**
 * Отметки, которыми помечаются админские запросы, чтобы предоставить данные для определенных методов, а не как пункты меню
 * {@link #DELETE} - запрос на удаление контакта}
 * {@link #APPOINT_GUARDIAN} - запрос на назначение опекуном
 * {@link #CREATE} - запрос на добавление конакта}
 * {@link #BACK} - Вернуться в главное меню}
 * {@link #EXTEND_PROBATION} - Запрос на продление испытательного срока}
 */
public enum AdminResponses {
    DELETE,
    APPOINT_GUARDIAN,
    CREATE,
    BACK,
    PROBATION_FAIL, REPORTS_BY_DAY, REPORTS_BY_USER, EXTEND_PROBATION
}
