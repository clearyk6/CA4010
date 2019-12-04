# Cluster Analysis

Extracting Infor from unabelled, untrained data

- nice to see visually
- really good for anomally detection (spot outliers)

## Data Matrix

.csv fie

## Dissimilarity Matrix 

- Mark refers to this as _Similarity Matrix_ - measure of object by similarity

object-by-variable structure, stores collection of proximities that are available for all pairs of _n_ objects. Represented by an _n_ x _n_ table

- represents _n_ objects

- d(i,j) represents the measured **difference**/dissimilarity between objects i and j.
- d(i,i) = 0; they are the same, completely similar

rows and columns of data matrix represnt different entities, while dissimilarity matrix represent the same entities

Many clustering algorithms operate on dissimilarity matrix

**Interval-scaled** variables = continuous measurements of roughly linear scale. Weight/Height, Latitude/Longitude co-ords, weather temp etc

data should be **standardised** to give all variables an _equal weight_. Useful when no prior knowledge of data

- convert original measurements to **unitless** variables. Original scale out the window.
    1. Calculate **mean absolute deviation** - more robust to outliers than standard deviation. Deviations from the mean are _not squared_
    2. Calculate the **z score** (standardised measurements)

    - median absolute deviation is more robust again, but outliers can still be noted using mean a.d.

**Euclidean Distance**

After standardization, dissimilarity between the objects described by Interval Scaled vars is typically computed based on distance between each pair of objects

- **Manhattan Dist** another well known metric you could use. No squaring!

Both distances satisfy the following:
- distance non-negative
- dist of object to itself = 0
- Distance is a symmetric function (ab = ba)
- Triangular inequality.

**Binary Variable** two states: 0 and 1. 

- treating binary vars as interval scaled leads to misleading clustering results - _don't treat them as numbers_
- 2-by-2 contingency table where:
    - q = 0,0
    - r = 0,1
    - s = 1,0
    - t = 1,1
    - ... 
- binary var is symmetric if both its states are equally valuable and carry the same weigh: no preference on which outcome 
- asymmetric if outcomes of the states are not equally important. Most important (rarest) as 1, other by 0.
    - in asymmetric, value t is considered unimportant and discarded.

**Jaccard coefficient** coefficient d(i,j) where

### Categorical Variable

A generalization of the binary variable, but it can take on more than two states.

- let M = no. of possible states. States can be denoted by letters, symbols or set of integers
- Dissimilarity between i and j can be computed based on the **ratio of mismatches** 
    - Weights can be assigned to increase effect of m of to assign greater weight to specific states

**k-means algorithm**
- an _exclusive clustering algorithm_
- randomly select k of the objects, each initially represents a cluster mean or center - the **centroid**
- each remaining obj then assigned to the cluster to which it is most similar, based on the distance between the object and the cluster mean.
- Then compute the new mean for each cluster.
- process iterates until 'we no longer move'

Compute an **error** for each cluster. Clusters that change less between each iteration of the k-means algorithm have a lower error function.

**Centroid** - "the point"

**SUMMARY**

Basic clustering Problem is simple: wish to distribute objects into goups in which objs within each group are similar whereas groups themeslves are different.

k- means algorithm always terminates, but doesn't necessarily find the best clusters. Set of clusters with the smallest

### k-Medioids

- **notes 

**Representative Object**:

**Reassigning Representative Object**

Is o(random) a good replacement for o(j)?

- 
- 
- p currently belongs to rep obj o(i), i != j. if o(j) replaced by o(random) as a rep obj and p is still closest to o(i), then assignment does not change.
- p currently belongs to rep obj o(i), i If oj is replaced by o(random)

Cost function for k-medioids clustering. 

Each tim e a reassignment occurs ,a difference in the absolute error E is contributed to cost function

- cost function calculates diff in absolute erro rbalue if current rep o is replaced by a non-r o
- total cost of swapping = sum of costs incurred by all non-r os
    - if negative, then oj is replaced
    - if positive, try different r obj

**PAM - Partitioning Around Medioids** - determines k partitions for n objects

- choose k objects in D as initial rep 
- assign each remaining obj
- randomly select nonrepresentative obj
- compute total cost S of swapping rep obj with random
- if S < 0 then  swap o(j) with o(random), if not reselect random obj

after an initial random selection of k objects, algo repeatedly tries to make a better choice of cluster reps
- all possible pairs are analysed, where one object in each pair is considered a rep object and the other is not

k-medioids is more robust than k-means. Less noise n outliers, but processing is more costly...

**AHC: Agg **

Strt with each object in a cluster of its own, then repeatedly merge closest cluster pairs until just one cluster containing all remains.
- assign each obj to single obj cluster. Calculate distance
- choose closest pair of clusters and merge into one
- calculate distance between the new cluster and each of the og clusters
- repeat 2 and 3 until one all encompassing cluster remains

if N objects, there will be N-1 mergers.

This generates hierarchy of clusters.

**Dendogram** a binary tree to represent this hierarchical clustering process
- 'root' = single cluster at end.
- leaf nodes are the original single-object clusters.

**New Distance Table**

- ** 

Hypothesis test to calculate p-value

Discordancy test veriies whether an object is significantly large/small in relation to distribution F.

SIgnificance probability:
            
        SP()

(Case study: taxi company)