from transformers import AutoModelForPreTraining,AutoTokenizer
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import nltk
import string

useless_words = nltk.corpus.stopwords.words("english") + list(string.punctuation)
def Bag_Of_Word(words):
    words = nltk.word_tokenize(words)
    return [
        word for word in words \
        if not word in useless_words]

import spacy

def main():
    useless_words = nltk.corpus.stopwords.words("english") + list(string.punctuation)
    def build_bag_of_words_features_filtered(words):
        words = nltk.word_tokenize(words)
        return {
            word:1 for word in words \
            if not word in useless_words}


    with open('tech_data.txt', 'r') as file:
        # Read all lines from the file
        lines = file.readlines()

    # Combine the lines into a single string
    input_text = ''.join(lines)

    # Print the input text
    features = build_bag_of_words_features_filtered(input_text)
    # for f in features:
    #     print(f)

    #     print("----------------")
    example_str = ' '.join([f"{token}:{count}" for token, count in features.items()])
    model = AutoModelForPreTraining.from_pretrained("latest_model")

    tokenizer=AutoTokenizer.from_pretrained("latest_model")

    encoded = tokenizer.encode_plus(
            example_str,
            add_special_tokens=True,
            max_length=512,  # Adjust this based on your data and model limitations
            padding='max_length',
            truncation=True,
            return_attention_mask=True,
            return_tensors='pt'
        )

    input_ids=encoded['input_ids']
    attention_mask=encoded['attention_mask']
    from torch.utils.data import DataLoader, TensorDataset

    test_dataset = TensorDataset(input_ids, attention_mask)
    test_dataloader = DataLoader(test_dataset, batch_size=1)
    model.eval()
    
    import torch 
    import torch.nn.functional as F
    # output has more than 2 dimensions why?
    output = model(input_ids, attention_mask)
    probs = F.softmax(output.seq_relationship_logits,dim=1)


    
    nlp = spacy.load("en_core_web_md")


    datapoint_corpus = ["Diversity", "Equity", "Inclusion", "Innovation", "Empowerment","Joy","Excelling"]


    doc_datapoint = [nlp(word) for word in datapoint_corpus]

    target_corpus=Bag_Of_Word(input_text)
    doc_target = [nlp(word) for word in target_corpus]

    similarity_scores = []
    for doc1 in doc_datapoint:
        max_similarity = max(doc1.similarity(doc2) for doc2 in doc_target)
        similarity_scores.append(max_similarity)

    overall_similarity = np.mean(similarity_scores)

    maxi = max(0.6231127178319184,overall_similarity)
    mini = min(0.3124607077642623,overall_similarity)
    p_similarity =( overall_similarity - mini) /(maxi-mini) * 10

    print(p_similarity)
    # Tech is zero and Sales is 1
    return probs.detach().numpy(), np.argmax(probs.detach().numpy(),axis=1),p_similarity
print(main())

