package by.training.composite.service;

import by.training.composite.bean.Component;
import by.training.composite.bean.Text;
import by.training.composite.dao.parser.WordParser;
import by.training.composite.dao.parser.TextParser;
import by.training.composite.dao.parser.SentenceParser;
import by.training.composite.dao.parser.ParagraphParser;
import by.training.composite.dao.parser.LexemeParser;

import by.training.composite.dao.reader.DataReader;

/**
 * Factory tree.
 */
public class FactoryTree {
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
        WordParser wordParser = new WordParser();

        textParser.setNext(paragraphParser);
        paragraphParser.setNext(sentenceParser);
        sentenceParser.setNext(lexemeParser);
        lexemeParser.setNext(wordParser);
        textParser.parse(allText, text);
        return text;
    }
}
