import type { Book, GoogleBooksResponse, GoogleBookItem } from '../types'

const BASE_URL = 'https://www.googleapis.com/books/v1/volumes'
const API_KEY = 'AIzaSyCj6oYKt8P4dg3kp0QTVp0JHH_d_5RRjsM'

function parseBook(item: GoogleBookItem): Book {
  const v = item.volumeInfo
  return {
    id: item.id,
    title: v.title || 'Sem título',
    authors: v.authors || ['Autor desconhecido'],
    description: v.description || 'Sem descrição disponível.',
    thumbnail: v.imageLinks?.thumbnail?.replace('http://', 'https://') || '',
    categories: v.categories || [],
    publishedDate: v.publishedDate || '',
    pageCount: v.pageCount || 0,
    averageRating: v.averageRating || 0,
    ratingsCount: v.ratingsCount || 0,
    previewLink: v.previewLink || '',
  }
}

export async function searchBooks(query: string, maxResults = 20): Promise<Book[]> {
  const params = new URLSearchParams({
    q: query,
    maxResults: String(maxResults),
    langRestrict: 'pt',
    printType: 'books',
    orderBy: 'relevance',
    key: API_KEY,
  })
  const res = await fetch(`${BASE_URL}?${params}`)
  if (!res.ok) throw new Error('Erro ao buscar livros')
  const data: GoogleBooksResponse = await res.json()
  return (data.items || []).map(parseBook)
}

export async function getRecommendations(book: Book): Promise<Book[]> {
  const category = book.categories[0] || 'fiction'
  const author = book.authors[0] || ''
  const query = `subject:${category} OR inauthor:${author}`
  const params = new URLSearchParams({
    q: query,
    maxResults: '12',
    printType: 'books',
    orderBy: 'relevance',
    key: API_KEY,
  })
  const res = await fetch(`${BASE_URL}?${params}`)
  if (!res.ok) throw new Error('Erro ao buscar recomendações')
  const data: GoogleBooksResponse = await res.json()
  return (data.items || []).map(parseBook).filter((b) => b.id !== book.id).slice(0, 8)
}

export async function getBooksByGenre(genre: string, maxResults = 16): Promise<Book[]> {
  const params = new URLSearchParams({
    q: `subject:${genre}`,
    maxResults: String(maxResults),
    printType: 'books',
    orderBy: 'relevance',
    key: API_KEY,
  })
  const res = await fetch(`${BASE_URL}?${params}`)
  if (!res.ok) throw new Error('Erro ao buscar por gênero')
  const data: GoogleBooksResponse = await res.json()
  return (data.items || []).map(parseBook)
}
