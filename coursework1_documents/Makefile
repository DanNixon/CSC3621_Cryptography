TEX_COMPILER=xelatex
BIB_COMPILER=biber
GLOSSARIES_COMPILER=makeglossaries

TEX_FLAGS=-shell-escape -halt-on-error

DOCUMENT_SOURCE=$(wildcard *.tex)
DOCUMENT_PDF=$(DOCUMENT_SOURCE:.tex=.pdf)

IMAGES=$(wildcard *.mp)
IMAGES_PDF=$(IMAGES:.mp=-1.pdf)
IMAGES_1=$(IMAGES:.mp=.1)

RM=rm -rf

all: images_1 $(DOCUMENT_PDF)

clean:
	- $(RM) *.pdf
	- $(RM) *.log
	- $(RM) *.aux
	- $(RM) *.dvi
	- $(RM) *.bbl
	- $(RM) *.blg
	- $(RM) *.bm
	- $(RM) *.out
	- $(RM) *.nav
	- $(RM) *.snm
	- $(RM) *.toc
	- $(RM) *.gls
	- $(RM) *.glg
	- $(RM) *.glo
	- $(RM) *.ilg
	- $(RM) *.ind
	- $(RM) *.ist
	- $(RM) *.1
	- $(RM) *.xml
	- $(RM) *.bcf
	- $(RM) *.pyg

%.pdf: %.tex
	$(TEX_COMPILER) $(TEX_FLAGS) $<
  ifneq ("$(wildcard $(subst .tex,.bib,$<))","")
		$(BIB_COMPILER) $(subst .tex,,$<)
		$(TEX_COMPILER) $(TEX_FLAGS) $<
  endif

images_1: $(IMAGES_1)

%.1: %.mp
	mpost $<

images_pdf: $(IMAGES_PDF)

%-1.pdf: %.mp
	mptopdf $<
