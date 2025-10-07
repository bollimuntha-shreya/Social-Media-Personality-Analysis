# Social Media Personality Analysis

*Created for Megathon'23 KonnectNxt Problem Statement by Anush Anand, Bollimuntha Shreya, Mukta Chanda, Meghana Tedla, Ronak Dhingra.*

### Brief:
Social Media Personality Analysis is an Android App that attempts to analyze if a person is more fit for a tech job or a sales job based on his social media activities and a psychometric test. 
The app gives out a percentage the person fits into each categories, making it easier for job recruiters to choose potential candidates.

### Tech Stack:
- Frontend: Java + XML
- Data Extraction and Backend ML Model: Python

### Features:
- A form to enter social media credentials (currently implemented for Instagram).
- A python script then extracts all the words from the user's posts, profile page, and comments.
- A psychometric test that mathematically maps all questions to either tech or sales category and gives a percent outcome.
- The results of psychometric analysis and the extracted words from social media are inputted into a pre-trained BERT model that outputs the percent fit into sales job, tech job, and a culture score.
- The culture score gives more information about the candidate's personality.
- The results are displayed graphically.

### File Structure:
In `/app/src/main/`:
- java folder contains all Java code for the frontend.
- python folder contains social media scraper and BERT model.
- res folder contains XML layouts for the Android app.
