export interface Book {
  id: string
  title: string
  authors: string[]
  description: string
  thumbnail: string
  categories: string[]
  publishedDate: string
  pageCount: number
  averageRating: number
  ratingsCount: number
  previewLink: string
}

export interface GoogleBooksResponse {
  items: GoogleBookItem[]
  totalItems: number
}

export interface GoogleBookItem {
  id: string
  volumeInfo: {
    title: string
    authors?: string[]
    description?: string
    imageLinks?: {
      thumbnail?: string
      smallThumbnail?: string
    }
    categories?: string[]
    publishedDate?: string
    pageCount?: number
    averageRating?: number
    ratingsCount?: number
    previewLink?: string
  }
}

export type Genre = {
  label: string
  query: string
  emoji: string
}
