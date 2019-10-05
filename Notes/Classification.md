# Classification

## Overview

**Classification**: a _model_ (or _classifier_) is constructed to predict **categorical labels**, such as:

- a bank needing analysis of data in order to learn which loan applicants ar **safe** and which are **risky**
- a medical researcher wants to analyse cancer data in order to predict which one of three _specific_ treatments a patient should receive, **A**, **B** or **C**.

The categories can be represented by **discrete values** - where the ordering among values has no meaning.

**Predictor**: a model constructed to predict a **continuous-valued function** or ordered value.

- if a marketing manager wants to predict _how much_ a given customer will spend, this is a data analysis task known as **numeric prediction**

### **Classification Process**

Classification is a **two-step process**

- **Learning Step/Training Phase** In the first step, a **classifier is built** describing a _predetermined set of data classes/concepts_.
 
  - a classification algorithm builds the classifier by analysing(learning) from a **training set** made up of _database tuples_ and their _associated class labels_.
  - tuple X is represented by an _n_-dimensional attribute vector X = (x1, x2, ..., xn), depicting _n_ measurements made on the tuple from _n_ databse attributes (A1, A2, ..., An).
  - each tuple X is assumed to belong to a _predefined class_ as determined by another database attribute called the **class label** attribute. This attribute is _discrete-valued_ and _unordered_. It is **categorical**; each value serves as a category/class 
  - **Learning**: the training data is analysed by a classification _algorithm_
  - tuples in training set referred to as **training tuples**, sellected from the database under analysis. In classiification, training tuples can be _samples, examples, instances, data points_ or _objects_.
  - Known as **supervised learning** if the _class label of each training tuple is provided_ - it is told the class for each training tuple.
  - **Unsupervised** learning (AKA **Clustering**): the class label of each training tuple is _not known_, and the number or set of classes to be learned may not be known in advance.
  - Learning step can also be viewed as the learning of a _mapping/function_, y = f(X), that can predict the associated class label y of given tuple X

- **Classification Phase**: test data is used to _estimate the accuracy of the classification rules_. If acceptable, the rules are used to classify new data.

  - First, the **predictive accuracy** of the classifier is estimated. 
  - **Test set** must be used, made up of _test tuples_ and their associated _class labels_. If we used the training set to measure this accuracy, the estimate would likely be optimistic as classifier _overfits the data_ - during learning it can incorporate some anomolies of the training data not present in the general data set.
  - Tuples are randomly selected from the general data set, independant of the training tuples.
  - The **accuracy** of a classifier on a given test set = **% of test set tuples that are correctly classified**.
    
    - The associated class label of each test tuple is compared with the learned classifier's class prediction for that tuple.
  - If this accuracy is considered acceptable, classifier can then be used to classify future _unkown_ or _unseesn_ data tuples where the class label is not known.

### **Numeric Prediction**

In prediction, **no class attributes** as the attribute for which values are being predicted are _continuous-valued (ordered)_. Attribute known as the **predicted attribute**.

Data prediction is also a two step process. As with classification, the training set used to build a predictor should **not** be used to assess its accuracy. Independant test set is required.

- Accuracy of a predictor is estimated by computing an **error** based on the _difference between the **predicted value** and the **known value** of y for each test tuple X.

## **Bayes Classifiers**

a family of simple probabilistic classifiers, stems from supervised learning where classes are predetermined. Build a data model which can then be used to classify new data. Model produced is usually in the form of a _decision tree_ or a _set of rules_.

**Issues with regard to the model**: Accuracy, Computational Cost & Scalability of Algorithm

### Everyday Classification

Divide a group of objects so each one is assigned to one of a number of **mutually exhaustive and exclusive** categories known as **classes**. Assigned to one and only one class. eg Players who play for teams in the same comp.

- Uses **probability theory** to determine the most likely classification; probability _ranges from 0(impossible) to 1(certain)_

  - Probability of 0.7 means after a long series of observations, probability is 0.7. Greater no. of tests ups accuracy. _A single test does not provide true classification._
  - in general, the sum of probabilities of a set of mutually exclusive & exhaustive events must be 1.
  - a **training set** constiutes the results rom sample trials that we use to predict the classification of unclassified instances.
  - **problem!**, if only one classification picked, any unseen istances are _classified in an identical fashion_.

### Prior Probability

Probability of a given event, calculated using the frequency of **desired event / all possible outcomes.

- only takes one attribute, need to cover multiple additional attributes by using..

### Conditional Probability (Posterior Probability)

The Probability of an event occurring **if** we know that an attribute has a particular value (or several variables have particular variables)

- e.g probability of a train being _on time_ if the _season = winter_. i.e the probability of both occuring in the **same instance**.
- = number of **co-ccurences** of both events / number of occurences of **given instance** (in this example, (on time && winter)/(winter))
- Posterior prb. because we can calculate for the classification **after** obtaining info about attribute with particular value. _Prior Probability is calculated before this info is available._
- Can use this to calculate most likely classification for an _unseen instance_.

### Naive Bayes Classifiers

A means of **combining prior and posterior probabilities** into a single formula.

- Use this method for each classification in turn & chose the classification with the **largest value**
- _invert previous formula_: in eg above, use conditional probability that the season in winter given the train is v late. P(winter | v late) is calculated as the **count** of both being true in the **same instance**, divided by the **number of instances for which class is v late**

        P(ci)xP(a1 = v2 | ci)xP(a2 = v2 | ci)x...xP(an = vn | ci)

        c = classification
        i = number of mutually exhaustive and exclusive classifications in set
        a = attributes
        n = number of attributes
        v = value of an attribute

- we calculate the Product above and then choose the classification with largest value.
- The calculations are _not_ probabilities - they do not add up to one; they are **porportional**
- **problem!** - estimating probabilities by relative frequencies can give poor estimates when small number of instances with a given attribute.. in extreme case where it is zero, posterior probability is inevitably also 0.
