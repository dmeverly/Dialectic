<p align="center">
  <img src="src/main/resources/emblem-mono-light.png" width="84" alt="" />
</p>

# Dialectic

**Author**: David Everly  
**Language**: Java  
**Status**: Active development  

## Summary

Dialectic is a multiagent chat LLM pipeline implemented over SynapSys and designed to improve chat response quality.  The system takes user text query input and generates a default response, which is processed by 2 separate LLM agents tasked with creating logical argument in-favor or against the original response.  A final model considers the strengths, weaknesses, and assumptions of arguments and outputs the strongest argument as the final response.

---

## Purpose

To improve the quality of LLM chat responses by considering the strengths and weaknesses of the original response.

---

## System Architecture

```
Default Model Output
   │        │
   ▼        ▼
  Logic Models   
        │   
        ▼
      Judge
        │
        ▼
      User
```

---

## Developer & Deployment Notes

* This project is currently in experimental stage of development
* Planned extension and front-end integration

---

## License

This project is licensed under the **Apache License 2.0**.

You are free to use, modify, and distribute this software, including for
commercial purposes, subject to the terms of the license.

Private guard logic, policies, and runtime configurations are not included
in this repository and are not covered by this license.

Copyright © 2026  David Everly