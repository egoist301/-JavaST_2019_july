package by.training.composite.service;

import by.training.composite.bean.Component;
import by.training.composite.bean.Text;
import by.training.composite.dao.reader.DataReader;
import by.training.composite.service.parser.ParagraphParser;
import by.training.composite.service.parser.LexemeParser;
import by.training.composite.service.parser.SentenceParser;
import by.training.composite.service.parser.TextParser;
import by.training.composite.service.parser.SymbolParser;

/**
 * Factory tree.
 */
public final class FactoryTree {
    /**
     * Singleton.
     */
    private static final FactoryTree INSTANCE = new FactoryTree();

    /**
     * Default constructor.
     */
    private FactoryTree() {

    }

    /**
     * Getter.
     *
     * @return factory.
     */
    public static FactoryTree getINSTANCE() {
        return INSTANCE;
    }

    /**
     * Create text.
     *
     * @param filePath file path.
     * @return component.
     */
    public Component createText(final String filePath) {
        DataReader dataReader = new DataReader();
        String allText = dataReader.readAll(filePath);
        Text text = new Text();
        TextParser textParser = new TextParser();
        ParagraphParser paragraphParser = new ParagraphParser();
        SentenceParser sentenceParser = new SentenceParser();
        LexemeParser lexemeParser = new LexemeParser();
        SymbolParser symbolParser = new SymbolParser();

        textParser.setNext(paragraphParser);
        paragraphParser.setNext(sentenceParser);
        sentenceParser.setNext(lexemeParser);
        lexemeParser.setNext(symbolParser);
        textParser.parse(allText, text);
        return text;
    }
}
